<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model common\models\Ementa */

$this->title = 'Create Ementa';
$this->params['breadcrumbs'][] = ['label' => 'Ementas', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="ementa-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
