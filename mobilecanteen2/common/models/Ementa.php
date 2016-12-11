<?php

namespace common\models;

use Yii;

/**
 * This is the model class for table "ementa".
 *
 * @property integer $id
 * @property string $diadasemana
 * @property string $data
 * @property string $refeicao
 * @property string $sopa
 * @property string $carne
 * @property string $peixe
 * @property string $vegetariano
 * @property string $sobremesa
 * @property string $haementa
 */
class Ementa extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'ementa';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['diadasemana', 'data', 'refeicao', 'sopa', 'carne', 'peixe', 'vegetariano', 'sobremesa'], 'required'],
            [['haementa'], 'string', 'max' => 1],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'diadasemana' => 'Diadasemana',
            'data' => 'Data',
            'refeicao' => 'Refeicao',
            'sopa' => 'Sopa',
            'carne' => 'Carne',
            'peixe' => 'Peixe',
            'vegetariano' => 'Vegetariano',
            'sobremesa' => 'Sobremesa',
            'haementa' => 'Haementa',
        ];
    }

    public function uploadementa() {

        //elimina as ementas todas da base de dados
        // Ementa::deleteAll();

        if(($segundaalmoco = static::findOne('1')) == null) {
			$segundaalmoco = new Ementa();
		}
		
		if(($segundajantar = static::findOne('2')) == null) {
			$segundajantar = new Ementa();
		}
		if(($tercaalmoco = static::findOne('3')) == null) {
			$tercaalmoco = new Ementa();
		}
		if(($tercajantar = static::findOne('4')) == null) {
			$tercajantar = new Ementa();
		}
		if(($quartaalmoco = static::findOne('5')) == null) {
			$quartaalmoco = new Ementa();
		}
		if(($quartajantar = static::findOne('6')) == null) {
			$quartajantar = new Ementa();
		}
		if(($quintaalmoco = static::findOne('7')) == null) {
			$quintaalmoco = new Ementa();
		}
		if(($quintajantar = static::findOne('8')) == null) {
			$quintajantar = new Ementa();
		}
		if(($sextaalmoco = static::findOne('9')) == null) {
			$sextaalmoco = new Ementa();
		}
		if(($sextajantar = static::findOne('10')) == null) {
			$sextajantar = new Ementa();
		}

        function getText ($position, $position2, $formattedcontent) {
            $aux = range($position, $position2);

            array_shift($aux);
            array_pop($aux);

            $aux2 = '';
            foreach ($aux as $value) {
                $aux2 = $aux2 . $formattedcontent[$value] . ' ';
            }

            return rtrim($aux2, " ");
        }

        $parser = new \Smalot\PdfParser\Parser();
        $pdf = $parser->parseFile('../../ementaspdf/Cantina-2.pdf');

        $pages = $pdf->getPages();
        $i = 0;
        foreach ($pages as $page) {

            $content = $page->getText();
            $contents = str_replace(' :', ':', $content);
            $contentss = str_replace(':', ';:', $contents);

            //have no idea what /([\n,]|:)+/ does but it works
            $contentexploded = preg_split("/([\n,]|:)+/", $contentss);

            //remove whitespaces at the end of the strings (dunno why it happens)
            $formattedcontent=array_map('trim',$contentexploded);


            //buscar todas as posições das palavras encontradas dentro do array
            $sopas = array_keys($formattedcontent, 'Sopa;');
            $carnes = array_keys($formattedcontent, 'Carne;');
            $peixes = array_keys($formattedcontent, 'Peixe;');
            $vegetarianos = array_keys($formattedcontent, 'Vegetariano;');
            $sobremesas = array_keys($formattedcontent, 'Sobremesa;');

            $aux = 0;

            if($i == 1) {
                //ou seja o pdf não é constante, e tem demasiadas alterações para o foreach continuar
                break;
            }

            ///////////////////////SEGUNDA ALMOCO///////////////////////

			$segundaalmoco->id = 1;
            $segundaalmoco->refeicao = 'almoco';
            $segundaalmoco->diadasemana = 'Segunda-feira';
            $position = array_search($segundaalmoco->diadasemana, $formattedcontent);
            $segundaalmoco->data = str_replace('ENCERRADO ENCERRADO', '', $formattedcontent[++$position], $count);
            if($count != 0) {
                $segundaalmoco->sopa = 'ENCERRADO';
                $segundaalmoco->carne = 'ENCERRADO';
                $segundaalmoco->peixe = 'ENCERRADO';
                $segundaalmoco->vegetariano = 'ENCERRADO';
                $segundaalmoco->sobremesa = 'ENCERRADO';
                $segundaalmoco->haementa = '0';
            } else {
                $segundaalmoco->sopa = getText($sopas[$aux], $carnes[$aux], $formattedcontent);
                $segundaalmoco->carne = getText($carnes[$aux], $peixes[$aux], $formattedcontent);
                $segundaalmoco->peixe = getText($peixes[$aux], $vegetarianos[$aux], $formattedcontent);
                $segundaalmoco->vegetariano = getText($vegetarianos[$aux], $sobremesas[$aux], $formattedcontent);

                // é sempre o mesmo se houver alguma alteração, poderá ser alterado no backoffice
                $segundaalmoco->sobremesa = 'Fruta | doce | iogurte';
                $aux++;
            }


            ///////////////////////SEGUNDA JANTAR///////////////////////

			$segundajantar->id = 2;
            $segundajantar->refeicao = 'jantar';
            $segundajantar->diadasemana = 'Segunda-feira';
            $position = array_search($segundajantar->diadasemana, $formattedcontent);
            $segundajantar->data = str_replace('ENCERRADO ENCERRADO', '', $formattedcontent[++$position], $count);
            if($count != 0) {
                $segundajantar->sopa = 'ENCERRADO';
                $segundajantar->carne = 'ENCERRADO';
                $segundajantar->peixe = 'ENCERRADO';
                $segundajantar->vegetariano = 'ENCERRADO';
                $segundajantar->sobremesa = 'ENCERRADO';
                $segundajantar->haementa = '0';
            } else {
                $segundajantar->sopa = getText($sopas[$aux], $carnes[$aux], $formattedcontent);
                $segundajantar->carne = getText($carnes[$aux], $peixes[$aux], $formattedcontent);
                $segundajantar->peixe = getText($peixes[$aux], $vegetarianos[$aux], $formattedcontent);
                $segundajantar->vegetariano = getText($vegetarianos[$aux], $sobremesas[$aux], $formattedcontent);
                $segundajantar->sobremesa = 'Fruta | doce | iogurte';
                $aux++;
            }


            ///////////////////////TERCA ALMOCO///////////////////////


			$tercaalmoco->id = 3;
            $tercaalmoco->refeicao = 'almoco';
            $tercaalmoco->diadasemana = 'Terça-feira';
            $position = array_search($tercaalmoco->diadasemana, $formattedcontent);
            $tercaalmoco->data = str_replace('ENCERRADO ENCERRADO', '', $formattedcontent[++$position], $count);

            if($count != 0) {
                $tercaalmoco->sopa = 'ENCERRADO';
                $tercaalmoco->carne = 'ENCERRADO';
                $tercaalmoco->peixe = 'ENCERRADO';
                $tercaalmoco->vegetariano = 'ENCERRADO';
                $tercaalmoco->sobremesa = 'ENCERRADO';
                $tercaalmoco->haementa = '0';
            } else {
                $tercaalmoco->sopa = getText($sopas[$aux], $carnes[$aux], $formattedcontent);
                $tercaalmoco->carne = getText($carnes[$aux], $peixes[$aux], $formattedcontent);
                $tercaalmoco->peixe = getText($peixes[$aux], $vegetarianos[$aux], $formattedcontent);
                $tercaalmoco->vegetariano = getText($vegetarianos[$aux], $sobremesas[$aux], $formattedcontent);
                $tercaalmoco->sobremesa = 'Fruta | doce | iogurte';
                $aux++;
            }



            ///////////////////////TERCA JANTAR///////////////////////


			$tercajantar->id = 4;
            $tercajantar->refeicao = 'jantar';
            $tercajantar->diadasemana = 'Terça-feira';
            $position = array_search($tercajantar->diadasemana, $formattedcontent);
            $tercajantar->data = str_replace('ENCERRADO ENCERRADO', '', $formattedcontent[++$position], $count);
            if($count != 0) {
                $tercajantar->sopa = 'ENCERRADO';
                $tercajantar->carne = 'ENCERRADO';
                $tercajantar->peixe = 'ENCERRADO';
                $tercajantar->vegetariano = 'ENCERRADO';
                $tercajantar->sobremesa = 'ENCERRADO';
                $tercajantar->haementa = '0';
            } else {
                $tercajantar->sopa = getText($sopas[$aux], $carnes[$aux], $formattedcontent);
                $tercajantar->carne = getText($carnes[$aux], $peixes[$aux], $formattedcontent);
                $tercajantar->peixe = getText($peixes[$aux], $vegetarianos[$aux], $formattedcontent);
                $tercajantar->vegetariano = getText($vegetarianos[$aux], $sobremesas[$aux], $formattedcontent);
                $tercajantar->sobremesa = 'Fruta | doce | iogurte';
                $aux++;
            }


            ///////////////////////QUARTA ALMOCO///////////////////////
			
			$quartaalmoco->id = 5;
            $quartaalmoco->refeicao = 'almoco';
            $quartaalmoco->diadasemana = 'Quarta-feira';
            $position = array_search($quartaalmoco->diadasemana, $formattedcontent);
            $quartaalmoco->data = str_replace('ENCERRADO ENCERRADO', '', $formattedcontent[++$position], $count);
            if($count != 0) {
                $quartaalmoco->sopa = 'ENCERRADO';
                $quartaalmoco->carne = 'ENCERRADO';
                $quartaalmoco->peixe = 'ENCERRADO';
                $quartaalmoco->vegetariano = 'ENCERRADO';
                $quartaalmoco->sobremesa = 'ENCERRADO';
                $quartaalmoco->haementa = '0';
            } else {
                $quartaalmoco->sopa = getText($sopas[$aux], $carnes[$aux], $formattedcontent);
                $quartaalmoco->carne = getText($carnes[$aux], $peixes[$aux], $formattedcontent);
                $quartaalmoco->peixe = getText($peixes[$aux], $vegetarianos[$aux], $formattedcontent);
                $quartaalmoco->vegetariano = getText($vegetarianos[$aux], $sobremesas[$aux], $formattedcontent);
                $quartaalmoco->sobremesa = 'Fruta | doce | iogurte';
                $aux++;
            }



            ///////////////////////QUARTA JANTAR///////////////////////

			$quartajantar->id = 6;
            $quartajantar->refeicao = 'jantar';
            $quartajantar->diadasemana = 'Quarta-feira';
            $position = array_search($quartajantar->diadasemana, $formattedcontent);
            $quartajantar->data = str_replace('ENCERRADO ENCERRADO', '', $formattedcontent[++$position], $count);
            if($count != 0) {
                $quartajantar->sopa = 'ENCERRADO';
                $quartajantar->carne = 'ENCERRADO';
                $quartajantar->peixe = 'ENCERRADO';
                $quartajantar->vegetariano = 'ENCERRADO';
                $quartajantar->sobremesa = 'ENCERRADO';
                $quartajantar->haementa = '0';
            } else {
                $quartajantar->sopa = getText($sopas[$aux], $carnes[$aux], $formattedcontent);
                $quartajantar->carne = getText($carnes[$aux], $peixes[$aux], $formattedcontent);
                $quartajantar->peixe = getText($peixes[$aux], $vegetarianos[$aux], $formattedcontent);
                $quartajantar->vegetariano = getText($vegetarianos[$aux], $sobremesas[$aux], $formattedcontent);
                $quartajantar->sobremesa = 'Fruta | doce | iogurte';
                $aux++;
            }



            ///////////////////////QUINTA ALMOCO///////////////////////

			$quintaalmoco->id = 7;
            $quintaalmoco->refeicao = 'almoco';
            $quintaalmoco->diadasemana = 'Quinta-feira';
            $position = array_search($quintaalmoco->diadasemana, $formattedcontent);
            $quintaalmoco->data = str_replace('ENCERRADO ENCERRADO', '', $formattedcontent[++$position], $count);
            if($count != 0) {
                $quintaalmoco->sopa = 'ENCERRADO';
                $quintaalmoco->carne = 'ENCERRADO';
                $quintaalmoco->peixe = 'ENCERRADO';
                $quintaalmoco->vegetariano = 'ENCERRADO';
                $quintaalmoco->sobremesa = 'ENCERRADO';
                $quintaalmoco->haementa = '0';
            } else {
                $quintaalmoco->sopa = getText($sopas[$aux], $carnes[$aux], $formattedcontent);
                $quintaalmoco->carne = getText($carnes[$aux], $peixes[$aux], $formattedcontent);
                $quintaalmoco->peixe = getText($peixes[$aux], $vegetarianos[$aux], $formattedcontent);
                $quintaalmoco->vegetariano = getText($vegetarianos[$aux], $sobremesas[$aux], $formattedcontent);
                $quintaalmoco->sobremesa = 'Fruta | doce | iogurte';
                $aux++;
            }


            ///////////////////////QUINTA JANTAR///////////////////////

			$quintajantar->id = 8;
            $quintajantar->refeicao = 'jantar';
            $quintajantar->diadasemana = 'Quinta-feira';
            $position = array_search($quintajantar->diadasemana, $formattedcontent);
            $quintajantar->data = str_replace('ENCERRADO ENCERRADO', '', $formattedcontent[++$position], $count);
            if($count != 0) {
                $quintajantar->sopa = 'ENCERRADO';
                $quintajantar->carne = 'ENCERRADO';
                $quintajantar->peixe = 'ENCERRADO';
                $quintajantar->vegetariano = 'ENCERRADO';
                $quintajantar->sobremesa = 'ENCERRADO';
                $quintajantar->haementa = '0';
            } else {
                $quintajantar->sopa = getText($sopas[$aux], $carnes[$aux], $formattedcontent);
                $quintajantar->carne = getText($carnes[$aux], $peixes[$aux], $formattedcontent);
                $quintajantar->peixe = getText($peixes[$aux], $vegetarianos[$aux], $formattedcontent);
                $quintajantar->vegetariano = getText($vegetarianos[$aux], $sobremesas[$aux], $formattedcontent);
                $quintajantar->sobremesa = 'Fruta | doce | iogurte';
                $aux++;
            }



            ///////////////////////SEXTA ALMOCO///////////////////////

			$sextaalmoco->id = 9;
            $sextaalmoco->refeicao = 'almoco';
            $sextaalmoco->diadasemana = 'Sexta-feira';
            $position = array_search($sextaalmoco->diadasemana, $formattedcontent);
            $sextaalmoco->data = str_replace('ENCERRADO ENCERRADO', '', $formattedcontent[++$position], $count);
            if($count != 0) {
                $sextaalmoco->sopa = 'ENCERRADO';
                $sextaalmoco->carne = 'ENCERRADO';
                $sextaalmoco->peixe = 'ENCERRADO';
                $sextaalmoco->vegetariano = 'ENCERRADO';
                $sextaalmoco->sobremesa = 'ENCERRADO';
                $sextaalmoco->haementa = '0';
            } else {
                $sextaalmoco->sopa = getText($sopas[$aux], $carnes[$aux], $formattedcontent);
                $sextaalmoco->carne = getText($carnes[$aux], $peixes[$aux], $formattedcontent);
                $sextaalmoco->peixe = getText($peixes[$aux], $vegetarianos[$aux], $formattedcontent);
                $sextaalmoco->vegetariano = getText($vegetarianos[$aux], $sobremesas[$aux], $formattedcontent);
                $sextaalmoco->sobremesa = 'Fruta | doce | iogurte';
                $aux++;
            }




            ///////////////////////SEXTA JANTAR///////////////////////

			$sextajantar->id = 10;
            $sextajantar->refeicao = 'jantar';
            $sextajantar->diadasemana = 'Sexta-feira';
            $position = array_search($sextajantar->diadasemana, $formattedcontent);
            $sextajantar->data = str_replace('ENCERRADO ENCERRADO', '', $formattedcontent[++$position], $count);
            if($count != 0) {
                $sextajantar->sopa = 'ENCERRADO';
                $sextajantar->carne = 'ENCERRADO';
                $sextajantar->peixe = 'ENCERRADO';
                $sextajantar->vegetariano = 'ENCERRADO';
                $sextajantar->sobremesa = 'ENCERRADO';
                $sextajantar->haementa = '0';
            } else {
                $sextajantar->sopa = getText($sopas[$aux], $carnes[$aux], $formattedcontent);
                $sextajantar->carne = getText($carnes[$aux], $peixes[$aux], $formattedcontent);
                $sextajantar->peixe = getText($peixes[$aux], $vegetarianos[$aux], $formattedcontent);
                $sextajantar->vegetariano = getText($vegetarianos[$aux], $sobremesas[$aux], $formattedcontent);
                $sextajantar->sobremesa = 'Fruta | doce | iogurte';
            }

            $segundaalmoco->save();
            $segundajantar->save();
            $tercaalmoco->save();
            $tercajantar->save();
            $quartaalmoco->save();
            $quartajantar->save();
            $quintaalmoco->save();
            $quintajantar->save();
            $sextaalmoco->save();
            $sextajantar->save();

            $i++;
        }
    }
}

