package utils;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import data.model.api.servicepackage1.ImageOne;
import data.model.api.servicepackage1.SpecificationOne;
import data.model.api.servicepackage2.Image;
import data.model.api.servicepackage2.ServicePackage;
import data.model.api.servicepackage2.Specification;

public class DataTypeConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Image> ImageToList(String data)
    {
        if (data == null){
            return Collections.emptyList();
        }

        Type listtype = new TypeToken<List<Image>>(){
        }.getType();

        return gson.fromJson(data,listtype);
    }

    @TypeConverter
    public static String ListToImage(List<Image> images){
        return gson.toJson(images);
    }

    @TypeConverter
    public static List<Specification> SpecificationToList(String data)
    {
        if (data == null){
            return Collections.emptyList();
        }

        Type listtype = new TypeToken<List<Specification>>(){
        }.getType();

        return gson.fromJson(data,listtype);
    }

    @TypeConverter
    public static String ListToSpecification(List<Specification> specifications)
    {
        return gson.toJson(specifications);
    }

    @TypeConverter
    public static List<ServicePackage> ServicePackageToList(String data)
    {
        if (data == null){
            return Collections.emptyList();
        }

        Type list = new TypeToken<List<ServicePackage>>(){
        }.getType();

        return gson.fromJson(data,list);
    }

    @TypeConverter
    public static String ListToServicePackage(List<ServicePackage> packages)
    {
        return gson.toJson(packages);
    }

    @TypeConverter
    public static List<ImageOne> ImageOneToList(String data)
    {
        if (data == null){
            return Collections.emptyList();
        }

        Type list = new TypeToken<List<ImageOne>>(){
        }.getType();

        return gson.fromJson(data,list);
    }

    @TypeConverter
    public static String ListToImageOne(List<ImageOne> packages)
    {
        return gson.toJson(packages);
    }

    @TypeConverter
    public static List<SpecificationOne> SpecificationOneToList(String data)
    {
        if (data == null){
            return Collections.emptyList();
        }

        Type list = new TypeToken<List<SpecificationOne>>(){
        }.getType();

        return gson.fromJson(data,list);
    }

    @TypeConverter
    public static String ListToSpecificationOne(List<SpecificationOne> packages)
    {
        return gson.toJson(packages);
    }

}
