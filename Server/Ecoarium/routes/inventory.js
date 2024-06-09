const express = require('express');
const router = express.Router();
const db = require('../models');
const { isLoggedIn } = require('./middlewares');

// 프로필 불러오기
router.get('/load-profile', isLoggedIn, async (req,res, next) => {
    try{
        let user = req.user;
        user = user.get({ plain: true });
        delete user.password; delete user.email; delete user.QRcode; delete user.verifCode;
        delete user.createdAt; delete user.updatedAt; delete user.deletedAt;
        const points = user.points;

        res.json({user, points});
    } catch (error) {
        console.error(error);
        return next(error);
    }
});

// 사용가능 불러오기
router.get('/available', isLoggedIn, async (req, res, next) => {
    try {
        //보유중인 상품들
        const available_items = await db.Inventory.findAll({
            where: {
                userId: req.user.id,
                state: 1,
            },
            order: [['createdAt', 'DESC']]
        });
        //보유중인 상품들의 id를 중복되지 않게 리스트에 저장
        const itemIds = new Set();
        for (const each of available_items) {
           itemIds.add(each.itemId); 
        };
        //중복되지 않는 각 상품들의 모델을 리스트에 저장
        const items = [];
        for (const each of [...itemIds]) {
            const item = await db.Store.findOne({where:{id: each}});
            items.push(item);
        };

        res.json({available_items, items});
    } catch (error) {
        console.error(error);
        return next(error);
    }
});

// 사용완료 불러오기
router.get('/completed', isLoggedIn, async (req, res, next) => {
    try {
        //사용완료한 상품들
        const completed_items = await db.Inventory.findAll({
            where: {
                userId: req.user.id,
                state: 0,
            },
            order: [['createdAt', 'DESC']]
        });
        //사용완료한 상품들의 id를 중복되지 않게 리스트에 저장
        const itemIds = new Set();
        for (const each of completed_items) {
           itemIds.add(each.itemId); 
        }; 
        //중복되지 않는 각 상품들의 모델을 리스트에 저장
        const items = [];
        for (const each of [...itemIds]) {
            const item = await db.Store.findOne({where:{id: each}});
            items.push(item);
        };

        res.json({completed_items, items});
    } catch (error) {
        console.error(error);
        return next(error);
    }
});

//-----------------------------------------
// 무한 스크롤

// 사용가능 불러오기
router.get('/partOfAvailable', isLoggedIn, async (req, res, next) => {
    try {
        //보유중인 상품들
        const page = parseInt(req.query.page) || 1;
        const limit = parseInt(req.query.limit) || 10;
        const offset = (page - 1) * limit;
        const available_items = await db.Inventory.findAll({
            where: {
                userId: req.user.id,
                state: 1,
            },
            order: [['createdAt', 'DESC']],
            limit: limit,
            offset: offset
        });
        //보유중인 상품들의 id를 중복되지 않게 리스트에 저장
        const itemIds = new Set();
        for (const each of available_items) {
           itemIds.add(each.itemId); 
        };
        //중복되지 않는 각 상품들의 모델을 리스트에 저장
        const items = [];
        for (const each of [...itemIds]) {
            const item = await db.Store.findOne({where:{id: each}});
            items.push(item);
        };

        res.json({available_items, items});
    } catch (error) {
        console.error(error);
        return next(error);
    }
});

// 사용완료 불러오기
router.get('/partOfCompleted', isLoggedIn, async (req, res, next) => {
    try {
        //사용완료한 상품들
        const page = parseInt(req.query.page) || 1;
        const limit = parseInt(req.query.limit) || 10;
        const offset = (page - 1) * limit;
        const completed_items = await db.Inventory.findAll({
            where: {
                userId: req.user.id,
                state: 0,
            },
            order: [['createdAt', 'DESC']],
            limit: limit,
            offset: offset
        });
        //사용완료한 상품들의 id를 중복되지 않게 리스트에 저장
        const itemIds = new Set();
        for (const each of completed_items) {
           itemIds.add(each.itemId); 
        }; 
        //중복되지 않는 각 상품들의 모델을 리스트에 저장
        const items = [];
        for (const each of [...itemIds]) {
            const item = await db.Store.findOne({where:{id: each}});
            items.push(item);
        };

        res.json({completed_items, items});
    } catch (error) {
        console.error(error);
        return next(error);
    }
});

module.exports = router;