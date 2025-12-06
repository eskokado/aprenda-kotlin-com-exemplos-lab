# Aprenda Kotlin Com Exemplos: Desafio de Projeto (Lab)

Desafio de Projeto criado para avaliação do conteúdo técnico explorado no repositório [aprenda-kotlin-com-exemplos](https://github.com/digitalinnovationone/aprenda-kotlin-com-exemplos).

**Domínio**
- A [DIO](https://web.dio.me) possui `Formacoes` compostas por `ConteudosEducacionais` orientados a uma stack específica.
- Cada formação possui `nome`, `nivel` e lista de `conteudosEducacionais` e permite `matricular` um ou mais `Alunos`.

**Solução Implementada**
- Modelos: `Nivel`, `ConteudoEducacional`, `Usuario`, `Formacao`.
- Operações: `matricular`, `concluir` conteúdo, `pontuacaoTotal` por usuário, `ranking` por formação e `top(n)`.
- Validações: `require` para usuário matriculado e conteúdo pertencente à formação.

**Como Executar**
- Pré‑requisito: `kotlinc` e `java` instalados.
- Compilar e executar:
  - `kotlinc desafio.kt -include-runtime -d desafio.jar`
  - `java -jar desafio.jar`

**Exemplo de Saída**
- `Ranking - Formação Kotlin Básica`
  - `1. Ana - 220 pontos`
  - `2. Bruno - 100 pontos`
  - `3. Carla - 80 pontos`
- `Ranking - Formação Kotlin Avançada`
  - `1. Ana - 150 pontos`
  - `2. Bruno - 120 pontos`
- `Top 2 - Formação Kotlin Básica`
  - `1. Ana - 220 pontos`
  - `2. Bruno - 100 pontos`
