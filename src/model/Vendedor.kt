package model

//Classe Vendedor - herda de Pessoa e Funcionario
class Vendedor(nome:String, cpf:String, dataNascimento:String, override var Matricula: String, var setor: String, override var salarioBase: Double = 1800.00): Pessoa(nome, cpf, dataNascimento), Funcionario{
    var salario: Double? = null
    //defini o salario base de 1800 para o vendedor
    override fun calcularSalario(valor: Double) {
        salario = salarioBase + valor
    }

    override fun exibirSalario(): String {
        return "o salario do vendedor é: $salario"
    }
}