package utils;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import data.model.api.servicepackage.Image;
import data.model.api.servicepackage.Specification;

public class DataTypeConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Image> ServicePackageImageToList(String data)
    {
        if (data == null){
            return Collections.emptyList();
        }

        Type listtype = new TypeToken<List<Image>>(){
        }.getType();

        return gson.fromJson(data,listtype);
    }

    @TypeConverter
    public static String ListToServicePackageImage(List<Image> images){
        return gson.toJson(images);
    }

    @TypeConverter
    public static List<Specification> SpecificationToList(String data)
    {
        if (data == null){
            return Collections.emptyList();
        }

        Type listtype = new TypeToken<List<Image>>(){
        }.getType();

        return gson.fromJson(data,listtype);
    }

    @TypeConverter
    public static String ListToSpecification(List<Specification> specifications)
    {
        return gson.toJson(specifications);
    }

}
