package com.devnull.roadsideeatery.db;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface MainDao {

    @Insert(onConflict = REPLACE)
    void insert(ItemData itemData);

    @Query("SELECT * FROM item_table")
    List<ItemData> getAll();

    @Query("DELETE FROM item_table WHERE id = :id")
    void delete(int id);

    @Query("SELECT * FROM item_table WHERE id = :id")
    ItemData getItem(int id);

}