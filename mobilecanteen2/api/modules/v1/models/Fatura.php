<?php

namespace api\modules\v1\models;

use \yii\db\ActiveRecord;


class Fatura extends ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'fatura';
    }

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
}
