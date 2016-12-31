package com.ementas.projecto.db;

import com.ementas.projecto.models.Fatura;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class FaturaDeserializer implements JsonDeserializer<Fatura> {

    @Override
    public Fatura deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject obj = json.getAsJsonObject();
        Fatura fatura = new Fatura();
        fatura.setId(obj.get("id").getAsLong());
        fatura.setData(obj.get("data").getAsString());
        fatura.setPreco(obj.get("preco").getAsDouble());
        fatura.setRefeicao(obj.get("refeicao").getAsString());
        fatura.setPrato(obj.get("prato").getAsString());

        return fatura;
    }
}
