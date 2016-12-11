<?php

namespace common\models;

use Yii;

/**
 * This is the model class for table "fatura".
 *
 * @property integer $id
 * @property string $data
 * @property double $preco
 * @property string $cantina
 * @property string $refeicao
 * @property string $prato
 * @property integer $id_user
 *
 * @property User $idUser
 */
class Fatura extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'fatura';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id', 'data', 'preco', 'cantina', 'refeicao', 'prato', 'id_user'], 'required'],
            [['id', 'id_user'], 'integer'],
            [['data'], 'safe'],
            [['preco'], 'number'],
            [['cantina', 'refeicao', 'prato'], 'string', 'max' => 45],
            [['id_user'], 'exist', 'skipOnError' => true, 'targetClass' => User::className(), 'targetAttribute' => ['id_user' => 'id']],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'data' => 'Data',
            'preco' => 'Preco',
            'cantina' => 'Cantina',
            'refeicao' => 'Refeicao',
            'prato' => 'Prato',
            'id_user' => 'Id User',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getIdUser()
    {
        return $this->hasOne(User::className(), ['id' => 'id_user']);
    }
}
