module.exports = (sequelize, DataTypes) => (
    sequelize.define('store', {
        name: {
            type: DataTypes.STRING(40),
            allowNull: true,
        },
        price: {
            type: DataTypes.INTEGER(1),
            allowNull: true,
        },
        img: {
            type: DataTypes.STRING(200),
            allowNull: true,
        },
    }, {
      timestamps: true,
      paranoid: true,
    })
  );