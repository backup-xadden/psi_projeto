<?php
namespace console\controllers;

use Yii;
use yii\console\Controller;

class RbacController extends Controller
{
    public function actionInit()
    {
        $auth = Yii::$app->authManager;

        $uploadEmenta = $auth->createPermission('uploadEmenta');
        $uploadEmenta->description = 'Upload ementa nova';
        $auth->add($uploadEmenta);

        $admin = $auth->createRole('admin');
        $auth->add($admin);
        $auth->addChild($admin, $uploadEmenta);

        $auth->assign($admin, 1);
    }
}