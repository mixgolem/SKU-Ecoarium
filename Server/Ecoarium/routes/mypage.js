const express = require('express');
const router = express.Router();
const db = require('../models');
const { sequelize } = require('../models');
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

// 전체 기록 불러오기
router.get('/load-all-logs', isLoggedIn, async (req,res, next) => {
    try{
        const point_earnings = await db.Point_earning.findAll({where: {userId: req.user.id}});
        const point_usages = await db.Point_usage.findAll({where: {userId: req.user.id}});
        const itemIds = new Set();
        const items = [];
        //보유한 상품들의 id를 중복되지 않게 리스트에 저장
        for (const each of point_usages) {
            itemIds.add(each.itemId);
        };
        //중복되지 않는 각 상품들의 모델을 리스트에 저장
        for (const each of [...itemIds]) {
            const item = await db.Store.findOne({where:{id: each}});
            items.push(item);
        };
        //전체기록
        const all_logs = [...point_earnings, ...point_usages];
        //전체기록 날짜순 정렬
        all_logs.sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));

        res.json({all_logs, items});
    } catch (error) {
        console.error(error);
        return next(error);
    }
});

// 획득 기록 불러오기
router.get('/load-earnings-logs', isLoggedIn, async (req,res, next) => {
    try{
        const point_earnings = await db.Point_earning.findAll({
            where: { userId: req.user.id },
            order: [['createdAt', 'DESC']]
          });
        res.json({point_earnings});
    } catch (error) {
        console.error(error);
        return next(error);
    }
});

// 사용 기록 불러오기
router.get('/load-usages-logs', isLoggedIn, async (req,res, next) => {
    try{
        //기록
        const point_usages = await db.Point_usage.findAll({
            where: {userId: req.user.id},
            order: [['createdAt', 'DESC']]
        });
        //기록된 상품들의 id를 중복되지 않게 리스트에 저장
        const itemIds = new Set();
        for (const each of point_usages) {
            itemIds.add(each.itemId);
        };
        //중복되지 않는 각 상품들의 모델을 리스트에 저장
        const items = [];
        for (const each of [...itemIds]) {
            const item = await db.Store.findOne({where:{id: each}});
            items.push(item);
        };

        res.json({point_usages, items});
    } catch (error) {
        console.error(error);
        return next(error);
    }
});

//-----------------------------------------
// 무한 스크롤

// 전체 기록 불러오기
router.get('/load-all-partOfLogs', isLoggedIn, async (req,res, next) => {
    try{
        const userId = req.user.id;
        const page = parseInt(req.query.page) || 1;
        const limit = parseInt(req.query.limit) || 10;
        const offset = (page - 1) * limit;
        // SQL 쿼리
        const query = `
            SELECT id, 'point_earning' AS type, location AS detail, createdAt FROM point_earnings WHERE userId = :userId
            UNION ALL
            SELECT id, 'point_usage' AS type, itemId AS detail, createdAt FROM point_usages WHERE userId = :userId
            ORDER BY createdAt DESC
            LIMIT :limit OFFSET :offset
        `;

        // 전체 기록
        const all_logs = await sequelize.query(query, {
            replacements: { userId, limit, offset },
            type: sequelize.QueryTypes.SELECT,
        });
        const itemIds = new Set();
        const items = [];
        //보유한 상품들의 id를 중복되지 않게 리스트에 저장
        for (const each of all_logs) {
            if (each.type == "point_usage") {
                itemIds.add(each.detail);
            }
        };
        //중복되지 않는 각 상품들의 모델을 리스트에 저장
        for (const each of [...itemIds]) {
            const item = await db.Store.findOne({where:{id: each}});
            items.push(item);
        };
        res.json({all_logs, items});
    } catch (error) {
        console.error(error);
        return next(error);
    }
});

// 획득 기록 불러오기
router.get('/load-earnings-partOfLogs', isLoggedIn, async (req,res, next) => {
    try{
        const page = parseInt(req.query.page) || 1;
        const limit = parseInt(req.query.limit) || 10;
        const offset = (page - 1) * limit;
        const point_earnings = await db.Point_earning.findAll({
            where: { userId: req.user.id },
            order: [['createdAt', 'DESC']],
            limit: limit,
            offset: offset
          });
        res.json({point_earnings});
    } catch (error) {
        console.error(error);
        return next(error);
    }
});

// 사용 기록 불러오기
router.get('/load-usages-partOfLogs', isLoggedIn, async (req,res, next) => {
    try{

        const page = parseInt(req.query.page) || 1;
        const limit = parseInt(req.query.limit) || 10;
        const offset = (page - 1) * limit;
        //기록
        const point_usages = await db.Point_usage.findAll({
            where: {userId: req.user.id},
            order: [['createdAt', 'DESC']],
            limit: limit,
            offset: offset
        });
        //기록된 상품들의 id를 중복되지 않게 리스트에 저장
        const itemIds = new Set();
        for (const each of point_usages) {
            itemIds.add(each.itemId);
        };
        //중복되지 않는 각 상품들의 모델을 리스트에 저장
        const items = [];
        for (const each of [...itemIds]) {
            const item = await db.Store.findOne({where:{id: each}});
            items.push(item);
        };

        res.json({point_usages, items});
    } catch (error) {
        console.error(error);
        return next(error);
    }
});

module.exports = router;