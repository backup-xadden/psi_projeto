package com.ementas.projecto.db;

import com.ementas.projecto.models.Ementa;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class EmentaDeserializer implements JsonDeserializer<Ementa> {

    @Override
    public Ementa deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject obj = json.getAsJsonObject();
        Ementa ementa = new Ementa();
        ementa.setId(obj.get("id").getAsLong());
        ementa.setData(obj.get("data").getAsString());
        ementa.setDiadasemana(obj.get("diadasemana").getAsString());
        ementa.setRefeicao(obj.get("refeicao").getAsString());
        ementa.setSopa(obj.get("sopa").getAsString());
        ementa.setCarne(obj.get("carne").getAsString());
        ementa.setPeixe(obj.get("peixe").getAsString());
        ementa.setVegetariano(obj.get("vegetariano").getAsString());
        ementa.setSobremesa(obj.get("sobremesa").getAsString());
        ementa.setHaementa(obj.get("haementa").getAsInt());

        return ementa;
    }
}
