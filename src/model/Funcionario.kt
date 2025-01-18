package model

// interface Funcionario

interface Funcionario{
    var Matricula: String
    var salarioBase: Double

    fun calcularSalario(valor: Double)

    fun exibirSalario():String

}