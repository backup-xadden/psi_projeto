<?php

/* @var $this yii\web\View */
use yii\helpers\Url;

$this->title = 'Back Office';
?>
<div class="site-index">
    <div>
        <p>Meta ficheiro pdf da ementa na pasta mobilecanteen2/ementaspdf com o nome Cantina-2.pdf</p>
        <p><a class="btn btn-lg btn-success" href="<?php echo Url::to(['ementa/ementa']);?>">Upload nova ementa</a></p>
    </div>
</div>
