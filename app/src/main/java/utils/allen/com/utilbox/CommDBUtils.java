package utils.allen.com.utilbox;

/**
 * Created by Administrator on 2017/6/21.
 */

import android.content.Context;

import net.tsz.afinal.FinalDb;
import net.tsz.afinal.db.sqlite.DbModel;

import java.util.List;
import java.util.Map;

/*import net.tsz.afinal.FinalHttp;
 import net.tsz.afinal.http.AjaxCallBack;*/

/*import com.mobitax.util.CommUtil;

 import com.mobitax.application.GlobleApplication;*/

public class CommDBUtils {


    public void insert(Class object, Context context) {


        FinalDb db = FinalDb.create(context);
        db.save(object);

    }


    public void update(Class object, Context context) {

        FinalDb db = FinalDb.create(context);
        db.update(object);
    }

    public List<Object> SelectAll(Class object, Context context, String orderBy) {

        FinalDb db = FinalDb.create(context);
        if (CommUtils.isNullOrBlank(orderBy)) {
            return db.findAll(object);
        } else {
            return db.findAll(object, orderBy);
        }


    }

/*
*精确查找=
*/

    public Object SelectOne(Class object, Context context, Map<String, String> columnName) {

        FinalDb db = FinalDb.create(context);

        String where = " where 1=1 ";

        for (Map.Entry<String, String> entry : columnName.entrySet()) {
            where = where + " and " + entry.getKey() + " = " + entry.getValue();

        }


        return db.findAllByWhere(object, where);


    }



    /*
*模糊查找 like
*/

    public List<Object> SelectALLLike(Class object, Context context, Map<String, String> columnName) {

        FinalDb db = FinalDb.create(context);

        String where = " where 1=1 ";

        for (Map.Entry<String, String> entry : columnName.entrySet()) {
            where = where + " and " + entry.getKey() + " like " + entry.getValue();

        }

        return db.findAllByWhere(object, where);
    }

/*
*精确查找=
*/

    public List<Object> SelectALLByColumnName(Class object, Context context, Map<String, String> columnName) {

        FinalDb db = FinalDb.create(context);

        String where = " where 1=1 ";

        for (Map.Entry<String, String> entry : columnName.entrySet()) {
            where = where + " and " + entry.getKey() + " = " + entry.getValue();

        }

        return db.findAllByWhere(object, where);


    }



    /*
*删除操作（可按条件）
*/

    public void deleteALL(Class object, Context context, Map<String, String> columnName) {

        FinalDb db = FinalDb.create(context);

        String where = " where 1=1 ";

        for (Map.Entry<String, String> entry : columnName.entrySet()) {
            where = where + " and " + entry.getKey() + " = " + entry.getValue();

        }

        db.deleteByWhere(object, where);


    }



        /*
*自定义SQL语句
*/

    public List<DbModel> UserSQL(Class object, Context context, String SQL) {

        FinalDb db = FinalDb.create(context);

        DbModel Model=new DbModel();

       return  db.findDbModelListBySQL(SQL);


    }
}