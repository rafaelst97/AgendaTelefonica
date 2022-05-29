package br.univali.pdm.agendatelefonica.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.univali.pdm.agendatelefonica.model.Contato

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object{
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "lista.db"

        private val TABLE_NAME="Contato"
        private val COL_ID="Id"
        private val COL_NOME="Name"
        private val COL_TELEFONE="Email"
        private val COL_TIPO="Tipo"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY: String = ("CREATE TABLE " + TABLE_NAME+ "(" +
                COL_ID +" INTEGER PRIMARY KEY," + COL_NOME+" TEXT," +
                COL_TELEFONE + " TEXT," + COL_TIPO + " INTEGER)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        }
        onCreate(db!!)
    }

    val todosContatos:List<Contato>
        @SuppressLint("Range")
        get(){
            val contatos = ArrayList<Contato>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val db:SQLiteDatabase = this.writableDatabase
            val cursor: Cursor = db.rawQuery(selectQuery, null)
            val contatoPessoa = Contato()
            if (cursor.moveToFirst()) {
                do {
                    contatoPessoa.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    contatoPessoa.nome = cursor.getString(cursor.getColumnIndex(COL_NOME))
                    contatoPessoa.telefone = cursor.getString(cursor.getColumnIndex(COL_TELEFONE))
                    contatoPessoa.tipo = cursor.getInt(cursor.getColumnIndex(COL_TIPO))

                    contatoPessoa.add(contatoPessoa)
                } while (cursor.moveToNext())
            }
            db.close()
            return listOf(contatoPessoa)
        }

    fun addContato(contato: Contato){
        val db: SQLiteDatabase = this.writableDatabase
        val valores = ContentValues()
        valores.put(COL_ID, contato.id)
        valores.put(COL_NOME, contato.nome)
        valores.put(COL_TELEFONE, contato.telefone)
        valores.put(COL_TIPO, contato.tipo)

        db.insert(TABLE_NAME, null, valores)
        db.close()
    }

    fun updateContato(contato: Contato): Int{
        val db: SQLiteDatabase = this.writableDatabase
        val valores = ContentValues()
        valores.put(COL_ID, contato.id)
        valores.put(COL_NOME, contato.nome)
        valores.put(COL_TELEFONE, contato.telefone)
        valores.put(COL_TIPO, contato.tipo)

        return db.update(TABLE_NAME, valores, "$COL_ID=?", arrayOf(contato.id.toString()))
    }

    fun deleteContato(contato: Contato){
        val db: SQLiteDatabase = this.writableDatabase


        db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(contato.id.toString()))
        db.close()
    }

}