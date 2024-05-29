module.exports = (sequelize, DataTypes) => (
    sequelize.define('user', {
      username: {
        type: DataTypes.STRING(40),
        allowNull: true,
        unique: true,
      },
      password: {
        type: DataTypes.STRING(100),
        allowNull: true,
      },
      nickname:{
        type: DataTypes.STRING(20),
        allowNull: true,
      },
      email: {
        type: DataTypes.STRING(40),
        allowNull: true,
      },
      points:{
        type: DataTypes.INTEGER(1),
        allowNull: true,
      },
      QRcode:{
        type: DataTypes.STRING(40),
        allowNull: true,
      },
      verifCode:{
        type: DataTypes.STRING(40),
        allowNull: true,
      },
      admin:{
        type: DataTypes.INTEGER(1),
        allowNull: true,
      },
    }, {
      timestamps: true,
      paranoid: true,
    })
  );