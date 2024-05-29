exports.isLoggedIn = (req, res, next) => {
    console.log("Session exists:", req.session);
    if (req.isAuthenticated()) {
        next();
    } else {
        res.redirect('/');
    }
};
exports.isNotLoggedIn = (req, res, next) => {
    console.log("Session exists:", req.session);
    if (!req.isAuthenticated()) {
        next();
    } else {
        res.redirect('/');
    }
};