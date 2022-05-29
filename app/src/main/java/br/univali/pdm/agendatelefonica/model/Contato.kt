package br.univali.pdm.agendatelefonica.model

class Contato {
    fun add(contatoPessoa: Contato) {
        TODO("Not yet implemented")
    }

    var id: Int = 0
    var nome:String?=null
    var telefone:String?=null
    var tipo:Int = 0

    constructor(id: Int, nome: String?, telefone: String?, tipo: Int) {
        this.id = id
        this.nome = nome
        this.telefone = telefone
        this.tipo = tipo
    }

    constructor()

}