<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model common\models\EmentaSearch */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="ementa-search">

    <?php $form = ActiveForm::begin([
        'action' => ['index'],
        'method' => 'get',
    ]); ?>

    <?= $form->field($model, 'id') ?>

    <?= $form->field($model, 'diadasemana') ?>

    <?= $form->field($model, 'data') ?>

    <?= $form->field($model, 'refeicao') ?>

    <?= $form->field($model, 'sopa') ?>

    <?php // echo $form->field($model, 'carne') ?>

    <?php // echo $form->field($model, 'peixe') ?>

    <?php // echo $form->field($model, 'vegetariano') ?>

    <?php // echo $form->field($model, 'sobremesa') ?>

    <?php // echo $form->field($model, 'haementa') ?>

    <div class="form-group">
        <?= Html::submitButton('Search', ['class' => 'btn btn-primary']) ?>
        <?= Html::resetButton('Reset', ['class' => 'btn btn-default']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
