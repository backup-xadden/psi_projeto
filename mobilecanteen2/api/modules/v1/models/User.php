<?php

namespace api\modules\v1\models;

use \yii\db\ActiveRecord;


class User extends ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'user';
    }

    public function rules()
    {
        return [
            [['id', 'username', 'auth_key', 'password_hash', 'password_reset_token', 'email', 'status', 'created_at', 'updated_at'], 'required'],
        ];
    }
}
