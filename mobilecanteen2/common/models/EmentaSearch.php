<?php

namespace common\models;

use Yii;
use yii\base\Model;
use yii\data\ActiveDataProvider;
use common\models\Ementa;

/**
 * EmentaSearch represents the model behind the search form about `common\models\Ementa`.
 */
class EmentaSearch extends Ementa
{
    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id'], 'integer'],
            [['diadasemana', 'data', 'refeicao', 'sopa', 'carne', 'peixe', 'vegetariano', 'sobremesa', 'haementa'], 'safe'],
        ];
    }

    /**
     * @inheritdoc
     */
    public function scenarios()
    {
        // bypass scenarios() implementation in the parent class
        return Model::scenarios();
    }

    /**
     * Creates data provider instance with search query applied
     *
     * @param array $params
     *
     * @return ActiveDataProvider
     */
    public function search($params)
    {
        $query = Ementa::find();

        // add conditions that should always apply here

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
        ]);

        $this->load($params);

        if (!$this->validate()) {
            // uncomment the following line if you do not want to return any records when validation fails
            // $query->where('0=1');
            return $dataProvider;
        }

        // grid filtering conditions
        $query->andFilterWhere([
            'id' => $this->id,
        ]);

        $query->andFilterWhere(['like', 'diadasemana', $this->diadasemana])
            ->andFilterWhere(['like', 'data', $this->data])
            ->andFilterWhere(['like', 'refeicao', $this->refeicao])
            ->andFilterWhere(['like', 'sopa', $this->sopa])
            ->andFilterWhere(['like', 'carne', $this->carne])
            ->andFilterWhere(['like', 'peixe', $this->peixe])
            ->andFilterWhere(['like', 'vegetariano', $this->vegetariano])
            ->andFilterWhere(['like', 'sobremesa', $this->sobremesa])
            ->andFilterWhere(['like', 'haementa', $this->haementa]);

        return $dataProvider;
    }
}
