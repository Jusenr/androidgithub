package com.jusenr.androidlibrary.commn.database;

import android.app.Application;
import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.Collection;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.rx.RealmObservableFactory;

/**
 * Created by riven_chris on 2017/5/12.
 */

public class DBManager {

    public static void init(Application application) {
        Realm.init(application.getApplicationContext());
    }

    public static void initDefaultRealm(@NonNull String dbSuffix, int version) {
        initDefaultRealm(dbSuffix, version, null);
    }

    public static void initDefaultRealm(@NonNull String dbSuffix, int version, RealmMigration realmMigration) {
        Realm.removeDefaultConfiguration();
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder()
                .name("db_realm_" + dbSuffix)
                .schemaVersion(version)
                .rxFactory(new RealmObservableFactory());
//                .deleteRealmIfMigrationNeeded();
        if (realmMigration != null)
            builder.migration(realmMigration);

        RealmConfiguration realmConfiguration = builder.build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static Realm getDefaultRealm() {
        return Realm.getDefaultInstance();
    }

    public static <T extends RealmModel> T copyFromRealm(final Realm realm, final T model) {
        realm.beginTransaction();
        T unManagedModel = realm.copyFromRealm(model);
        realm.commitTransaction();
        return unManagedModel;
    }

    public static <T extends RealmModel> T copyToRealm(final Realm realm, final T model) {
        realm.beginTransaction();
        T unManagedModel = realm.copyToRealm(model);
        realm.commitTransaction();
        return unManagedModel;
    }

    public static void insertOrUpdate(@NonNull Realm realm, @NonNull RealmModel... model) {
        insertOrUpdate(realm, Arrays.asList(model));
    }

    public static void insertOrUpdate(@NonNull Realm realm,
                                      @NonNull final Collection<? extends RealmModel> objects) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(objects);
            }
        });
    }

    public static void delete(Realm realm, final RealmModel model) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmObject.deleteFromRealm(model);
            }
        });
    }

    public static void delete(Realm realm, final RealmResults realmResults, final int position) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realmResults.deleteFromRealm(position);
            }
        });
    }

    public static void deleteAll(Realm realm, final RealmResults realmResults) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realmResults.deleteAllFromRealm();
            }
        });
    }

    public static void close(Realm realm) {
        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
    }

    public static void isAutoRefresh(Realm realm) {
        if (realm != null && !realm.isClosed()) {
            realm.isAutoRefresh();
        }
    }

    public static boolean isClosed(Realm realm) {
        if (realm == null) {
            return true;
        } else if (realm.isClosed()) {
            return true;
        }
        return false;
    }
}
