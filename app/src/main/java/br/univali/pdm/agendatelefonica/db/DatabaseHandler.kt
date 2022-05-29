package br.univali.pdm.agendatelefonica.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.icu.lang.UProperty.NAME
import android.os.Build.ID
import android.text.BoringLayout
import androidx.core.content.contentValuesOf
import br.univali.pdm.agendatelefonica.model.Contato
import kotlinx.coroutines.selects.select

class DatabaseHandler (ctx: Context): SQLiteOpenHelper(ctx, DB_NAME, null, DB_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY, $NAME TEXT);"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun addContato(contato: Contato): Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME, contato.nome)
        values.put(PHONE, contato.telefone)
        values.put(TYPE, contato.tipo)
        val _sucess = db.insert(TABLE_NAME, null, values)
        return (("$_success").toInt() != -1)
    }

    fun getContato(_id: Int): Contato{
        val contato = Contato()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME WHERE $ID = $_id"
        val cursor = db.rawQuery(selectQuery, null)
        cursor?.moveToFirst()
        contato.id = cursor.getInt(cursor.getColumnIndex(ID))
        contato.nome = cursor.getString(cursor.getColumnIndex(NAME))
        contato.telefone = cursor.getString(cursor.getColumnIndex(PHONE))
        contato.tipo = cursor.getInt(cursor.getColumnIndex(TYPE))
        cursor.close()
        return contato
    }

    fun updateContato(contato: Contato): Boolean{
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(NAME, contato.nome)
        }
        val _sucess = db.update(TABLE_NAME, values, ID + "=?", arrayOf(contato.id.toString())).toLong()
        db.close()
        return ("$SUCCESS").toInt() != -1
    }

    fun deleteContato(_id: Int): Boolean {
        val db = this.writableDatabase
        val _success = db.delete(TABLE_NAME, ID + "=?", arrayOf(_id.toString())).toLong()
        return ("$_success").toInt() != -1
    }

    fun deleteAllContato(): Boolean {
        val db = this.writableDatabase
        val _success = db.delete(TABLE_NAME, null,null).toLong()
        db.close()
        return ("$_success").toInt() != -1
    }

    companion object {
        private val DB_VERSION = 1
        private val DB_NAME = "ContatosTelefonicos"
        private val TABLE_NAME = "Contatos"
        private val ID = "Id"
        private val NAME = "Nome"
        private val PHONE = "Telefone"
        private val TYPE = "Tipo"
    }
}