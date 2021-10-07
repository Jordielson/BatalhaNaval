# BatalhaNaval

O projeto consiste de um jogo de batalha naval em que é disputado entre dois jogadores e têm que adivinhar em que quadrados estão os navios do oponente. Este projeto foi desenvolvido na disciplina de programação II do segundo período de Análise e Desenvolvimento de Sistemas. 

## Documentação
  - [Descrição do Projeto](https://docs.google.com/document/d/1vwMQrBFqYKuszYl9MAdVNCW7MP1vIyYh/edit?usp=sharing&ouid=115335802043778949674&rtpof=true&sd=true)
  - [Relatório Final](https://docs.google.com/document/d/1UrCQBKCynIbVkoHffIKI_R_XLnWRlUcDWuycG6tIKkg/edit?usp=sharing)

## Desafios
Neste projeto o professor passou dois desafios que estão na [Descrição do Projeto](https://docs.google.com/document/d/1vwMQrBFqYKuszYl9MAdVNCW7MP1vIyYh/edit?usp=sharing&ouid=115335802043778949674&rtpof=true&sd=true). 

O desafio I é dos barcos formar embarcações não foi encontrado muita dificuldade em resolver, a solução foi feita através do polimorfismo no qual teríamos a classe Embarcação e as subclasses EmbaracaçãoComDois e EmbarcaçãoComTrês. 

O desafio II seria a inteligência do computador que após acertar uma embarcação ele continuar atacando ao redor do acerto, a solução para esse desafio foi limitar, após o acerto, em uma lista as casas de ataque do computador. Assim, enquanto a embarcação não for destruída totalmente os ataque do computador ficam limitadas as posições que estão na lista.

## Como Executar
  - Clone este [repositório](https://github.com/joaopaulopbjp/ifpb_monteiro_dac_2021_2_grupo_1.git) do github na seu computador
  - Entre na pasta e execute a classe Main, localizada em src/main/Main.java
