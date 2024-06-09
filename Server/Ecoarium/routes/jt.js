const express = require('express');
const multer = require('multer');
const path = require('path');
const router = express.Router();
const { exec } = require('child_process');
const db = require('../models');

const upload = multer({
    storage: multer.diskStorage({
        destination(req, file, cb) {
            cb(null, 'images/');
        },
        filename(req, file, cb) {
            cb(null, file.originalname);
        },
    }),
    limits: { fileSize: 5 * 1024 * 1024 },
});

// 유저 인식
router.post('/QRCode', async (req, res, next) => {
    const {QRCode, key} = req.body;
    console.log(key);
    console.log(QRCode);
    if (key != process.env.JT_SECRET) return;
    try{
        const userId = QRCode.substring(0, String(QRCode).length - 23);
        let user = await db.User.findOne({ where : {Id: userId} });
        const qrTime = parseInt(QRCode.slice(0, -10).slice(-13));
        const TimeOut = function() {
            const nowTime = new Date().getTime();
            const timeDifference = nowTime - qrTime;
            return timeDifference >= 60000;
        };
        if (user == null){
            // 유저 식별오류
            res.json(1);
        }
        else if (QRCode != user.QRcode) {
            // QRcode 값 불일치
            res.json(2);
        }
        else if (TimeOut()) {
            // QRcode 유효시간 초과
            res.json(3);
        }
        else {
            // 유저 인증 성공
            user = user.get({ plain: true });
            delete user.password; delete user.QRcode; delete user.admin; delete user.verifCode;
            delete user.createdAt; delete user.updatedAt; delete user.deletedAt;
            //디비 qr 제거
            await db.User.update({
                QRcode: null,
                }, {
                    where: {
                        Id: user.id,
                    }
            });
            res.json(user);
        }
    } catch (error) {
        console.error(error);
        return next(error);
    }
});

// 모델 검사
router.post('/determine', upload.single('file'), async (req, res) => {
    const {key, userId, location} = req.body;
    console.log(userId, location);
    if (key != process.env.JT_SECRET) return;
    try{
        if (!req.file) {
            return res.json(1); // 사진 전송 오류
        }
        // 업로드된 이미지 파일 이름
        const imageName = req.file.originalname;
        // 파이썬 파일 실행 명령어 (가상 환경 활성화 후 실행)
        const pythonScriptCommand = `python model.py images/"${imageName}"`;
                
        exec(`${pythonScriptCommand}`, async (error, stdout, stderr) => {
            if (error) {
                console.error(`exec error: ${error}`);
                return res.json(2); // 모델 실행 오류
            }
            // stdout에서 예측값 추출
            const pattern = /Prediction: \[\[(\d+\.\d+)\]\]/;
            const matches = stdout.match(pattern);
            if (matches) {
                // 정규식 패턴에 매칭된 값 출력
                const extractedValue = matches[1]; // 첫 번째 캡처 그룹 값
                console.log(extractedValue);
                res.json(extractedValue);
                if (parseFloat(extractedValue) > 0.5){
                    console.log("ng");
                } else {
                    // 최종 통과
                    console.log("clean");
                    const user = await db.User.findOne({ where : {Id: userId} });
                    console.log(user);
                    //포인트 + 1
                    const points = user.points
                    await db.User.update({
                        points: points + 1,
                    }, {
                        where: {
                        id: user.id,
                        }
                    });
                    //획득 기록 생성
                    await db.Point_earning.create({
                        location: location,
                        state: 1,
                        userId: user.id,
                    });
                };
            } else {
                console.log('No match found.');
                return res.json(3); // 모델 결과 오류
            }
        });

    } catch (error) {
        console.error(error);
        return next(error);
    }
});


module.exports = router;