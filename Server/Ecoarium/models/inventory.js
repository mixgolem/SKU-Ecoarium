module.exports = (sequelize, DataTypes) => (
    sequelize.define('inventory', {
        itemId: {
            type: DataTypes.INTEGER(1),
            allowNull: true,
        },
        state: {
            type: DataTypes.INTEGER(1),
            allowNull: true,
        },
        code: {
            type: DataTypes.STRING(16),
            allowNull: false,
            unique: true,
        }
    }, {
      timestamps: true,
      paranoid: true,
    })
  );