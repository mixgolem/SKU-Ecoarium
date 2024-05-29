module.exports = (sequelize, DataTypes) => (
    sequelize.define('point_earning', {
      location: {
        type: DataTypes.STRING(40),
        allowNull: true,
      },
    }, {
      timestamps: true,
      paranoid: true,
    })
  );