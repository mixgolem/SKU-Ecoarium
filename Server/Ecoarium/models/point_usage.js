module.exports = (sequelize, DataTypes) => (
    sequelize.define('point_usage', {
      itemId: {
        type: DataTypes.INTEGER(1),
        allowNull: true,
      },
    }, {
      timestamps: true,
      paranoid: true,
    })
  );