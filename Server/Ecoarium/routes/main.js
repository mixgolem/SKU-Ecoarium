const express = require('express');
const router = express.Router();
const db = require('../models');
const { isLoggedIn } = require('./middlewares');

// 포인트 불러오기
router.get('/loadpoint', isLoggedIn, async (req,res, next) => {
    try{
        const points = req.user.points
        res.json(points);
    } catch (error) {
        console.error(error);
        return next(error);
    }
});

// 메인 불러오기(모바일)
router.get('/loadPlasticStacks', isLoggedIn, async (req, res, next) => {
    try {
        const allStacks = await db.Point_earning.count();
        const myStacks = await db.Point_earning.count({
            where: {
                userId: req.user.id,
            }
        });
        res.json({ allStacks, myStacks });
    } catch (error) {
        next(error);
    }
});

// 포인트 생성 (디버깅용 임시)
router.get('/createpoint', isLoggedIn, async (req,res, next) => {
    try{
        //포인트 + 1
        const points = req.user.points
        await db.User.update({
            points: points + 1,
        }, {
            where: {
              id: req.user.id,
            }
        });
        //획득 기록 생성
        await db.Point_earning.create({
            location: "성결대점",
            type: 1,
            state: 1,
            userId: req.user.id,
        });
        res.redirect('/');
    } catch (error) {
        console.error(error);
        return next(error);
    }
});

// QR 생성
router.get('/createQR', isLoggedIn, async (req,res, next) => {
    try{
        const id = req.user.id;
        let now = new Date(); // 현재 날짜와 시간을 나타내는 Date 객체 생성
        let timestamp = now.getTime(); // 현재 시간의 타임스탬프를 밀리초 단위로 가져옴
        const value = id.toString() + timestamp.toString() + parseInt(Math.random()*10000000000);
        await db.User.update({
            QRcode: value,
            }, {
                where: {
                    Id: req.user.id,
                }
        });
        res.json(value);
    } catch (error) {
        console.error(error);
        return next(error);
    }
});


module.exports = router;
