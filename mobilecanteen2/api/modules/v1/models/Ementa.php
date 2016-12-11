<?php

namespace api\modules\v1\models;

use \yii\db\ActiveRecord;


class Ementa extends ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'ementa';
    }

    public function rules()
    {
        return [
            [['diadasemana', 'data', 'refeicao', 'sopa', 'carne', 'peixe', 'vegetariano', 'sobremesa'], 'required'],
            [['haementa'], 'string', 'max' => 1],
        ];
    }
}
