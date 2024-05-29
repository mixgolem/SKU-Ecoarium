const express = require('express');
const multer = require('multer');
const path = require('path');
const router = express.Router();
const db = require('../models');
const { isLoggedIn } = require('./middlewares');

const upload = multer({
    storage: multer.diskStorage({
        destination(req, file, cb) {
            cb(null, 'uploads/');
        },
        filename(req, file, cb) {
            const ext = path.extname(file.originalname);
            cb(null, path.basename(file.originalname, ext) + new Date().valueOf() + ext);
        },
    }),
    limits: { fileSize: 5 * 1024 * 1024 },
});

//상품 불러오기
router.get('/load', isLoggedIn, async (req,res, next) => {
    try{
        const item = await db.Store.findAll();
        res.json(item);
    } catch (error) {
        console.error(error);
        return next(error);
    }
});

//상품 생성
router.post('/newitem', isLoggedIn, upload.single('img'), async (req, res) => {
    try{
        const item = await db.Store.create({
            img: `/img/${req.file.filename}`,
            name: req.body.name,
            price: req.body.price,
        });
        await item.save();
        res.redirect('/admin');
    } catch (error) {
        console.error(error);
        return next(error);
    }
});

//상품 사용
router.put('/use', isLoggedIn, async (req, res) => {
    try{
        if (req.user.admin != 1) return;
        const item = await db.Inventory.findOne({where:{code: req.body.value}});
        if (item && item.state == 1) {
            await db.Inventory.update({
                state: 0,
            }, {
                where: {
                id:item.id,
                }
            });
            res.json(true);
        }
        else {
            res.json(false);
        }
    } catch (error) {
        console.error(error);
        return next(error);
    }
});



//파이썬 연결 테스트
router.post('/test', async (req, res, next) => {
    const {QRCode, key} = req.body;
    console.log(key);
    if (key != 2019) return;
    try{
        console.log(QRCode);
        res.json(true);
    } catch (error) {
        console.error(error);
        return next(error);
    }
});


module.exports = router;