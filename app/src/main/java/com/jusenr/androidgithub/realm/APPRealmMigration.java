package com.jusenr.androidgithub.realm;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by riven_chris on 2017/5/17.
 */

public class APPRealmMigration implements RealmMigration {

    public static final int VERSION = 3;

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        if (newVersion > oldVersion) {
            RealmSchema realmSchema = realm.getSchema();
            if (oldVersion == 1) {
                realmSchema.create("UpgradeModel")
                        .addField("localId", String.class, FieldAttribute.PRIMARY_KEY)
                        .addField("version", String.class)
                        .addField("url", String.class)
                        .addField("compulsive_upgrade", String.class)
                        .addField("description", String.class);
            }
            updateLearningModel(realmSchema);
        }
    }

    private void updateLearningModel(RealmSchema realmSchema) {
        realmSchema.get("RealmLearningModel")
                .addField("netId", String.class)
                .addField("learningState", boolean.class)
                .addField("start", String.class)
                .addField("end", String.class)
                .addField("repeat", String.class)
                .addField("nextDay", String.class);
    }

    @Override
    public int hashCode() {
        return 37;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof APPRealmMigration);
    }
}
