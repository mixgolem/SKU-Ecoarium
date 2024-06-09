const express = require('express');
const crypto = require('crypto');
const router = express.Router();
const db = require('../models');
const { isLoggedIn } = require('./middlewares');

// 상품 불러오기
router.get('/load', isLoggedIn, async (req,res, next) => {
    try{
        const item = await db.Store.findAll();
        res.json(item);
    } catch (error) {
        console.error(error);
        return next(error);
    }
});

// 상품 교환
router.put('/exchange', isLoggedIn, async (req,res, next) => {
    try{
        const points = req.user.points;
        const itemId = req.body.itemId;
        const item = await db.Store.findOne({where: {id: itemId}});

        if (item.price <= points){
            //포인트 차감
            
            await db.User.update({
                points: points - item.price,
            }, {
                where: {
                    id: req.user.id,
                }
            });
            //사용내역 생성
            await db.Point_usage.create({
                itemId: itemId,
                price: item.price,
                userId: req.user.id,
                img: item.img,
            });

            // 16자리 숫자 문자열 생성
            function generateUniqueNumberString() {
                const buffer = crypto.randomBytes(8);
                const numberString = BigInt('0x' + buffer.toString('hex')).toString().padStart(16, '0');
                return numberString.slice(0, 16); // 정확히 16자리 숫자 문자열로 자릅니다.
            }

            // 상품함에 상품 생성
            let code;
            while (true) {
                code = generateUniqueNumberString(); // 16자리 숫자 문자열 생성
                try {
                    await db.Inventory.create({
                        itemId: itemId,
                        state: 1,
                        userId: req.user.id,
                        code: code // 바코드 코드 저장
                    });
                    break; // 성공하면 루프 탈출
                } catch (error) {
                    if (error.name !== 'SequelizeUniqueConstraintError') {
                        throw error; // 다른 에러는 다시 throw
                    }
                    // 중복된 문자열이 발생하면 새로운 문자열을 생성하여 다시 시도
                }
            }
            res.json(true);
        }
        else{
            res.json(false);
        }
        
    } catch (error) {
        console.error(error);
        next(error);
    }
});

module.exports = router;