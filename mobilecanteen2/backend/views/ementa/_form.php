<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model common\models\Ementa */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="ementa-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'diadasemana')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'data')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'refeicao')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'sopa')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'carne')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'peixe')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'vegetariano')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'sobremesa')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'haementa')->textInput(['maxlength' => true]) ?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
