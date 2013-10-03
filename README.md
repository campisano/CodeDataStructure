GPGPU-for-dummies
=================



1   Introdução
=================
Executar código em placas gráficas requer conhecimentos específicos em programação e na arquitetura e funcionamento das mesmas. É preciso que o programa investigue o sistema para descobrir quais placas de vídeo podem ser usadas. Em seguida é preciso definir uma configuração de utilizo de acordo com as características da placa escolhida e as peculiaridades do problema que está querendo ser executado na mesma. Além disso, é preciso alocar memória para carregar dados em entrada, transferi-los na memória da placa gráfica, executar o programa na GPU, esperar a resposta e copiar o resultado da memória da placa gráfica para o programa para que possa ser apresentado ao utilizador.



2   Objetivo
=================
Objetivo do projeto é permitir o utilizo simples da computação em GPU de forma transparente ao utilizador, para que este não necessite saber programar para utilizar estes recursos.



3   Metodologia
=================
A linguagem C impõe uma curva de aprendizagem muito lenta e as implementações existentes quais CUDA e OpenCL obrigam o programador a configurar muitos detalhes antes de poder executar uma simples operação em GPU. 
 
A ideia é utilizar um subconjunto de operações de uma linguagem mais amigável para que o utilizador possa definir o cálculo para o seu problema.  A partir dai, o programa traduziria o cálculo para código em C, compilaria e executaria o cálculo. A escolha caiu na linguagem Java: uma linguagem muito difundida, multi-plataforma e fortemente tipada, coisa esta que facilitaria traduzir código a partir dela se comparada com Fortran ou linguagem de scripting. 

O programa poderia ser estruturado da seguinte forma: um editor de texto permitiria ao usuário de inserir o código do cálculo que quer executar em GPU. O código seria analisado para verificar que não possua erros e para entender quais dados em entrada seriam necessários para executar o cálculo. Em seguida seria traduzido para a linguagem C e compilado. Logo o programa pediria a fonte dos dados em entrada (por exemplo, um arquivo de texto formatado em colunas separadas por vírgula, CSV) e um destino para o resultado do cálculo (que poderia ser um outro arquivo de texto). Finalmente o código C compilado seria executado e o resultado seria apresentado para o usuário.

Resumindo essa estrutura prevê um programa pai que, a partir de um texto contendo código Java, traduza esse código em C e o execute. O programa pai poderia ser escrito em qualquer linguagem. Novamente foi escolhida a linguagem Java para ser de rápido desenvolvimento e multi-plataforma, além de possuir bibliotecas prontas para o escopo desse projeto. 

Para que o usuário possa inserir o código do próprio cálculo em Java foi testada a biblioteca Rsyntaxtextarea [1]. Ela permite a coloração de sintaxe e outras funcionalidades que permitem escrever o código Java de forma mais confortável. Outra possibilidade seria desfrutar das funcionalidades das IDEs quais Netbeans ou Eclipse, por exemplo criando este programa pai como fosse um plugin para estas.

Para analisar o código e traduzi-lo na linguagem C foi testada a biblioteca ASM [2]. Esta permite analisar e manipular o bytecode Java a partir de um arquivo do tipo '.class'. Apesar de reconhecer qualquer elemento da linguagem Java (tais como tipo de variáveis, herança e métodos), esta biblioteca não parece ter uma definição prática para as iterações, isto é, um while ou um for são traduzidos em jumps no bytecode, coisa que faz completamente sentido mas complica a tradução. Um exemplo prático para que utilizo de loop no código de cálculo do usuário é a multiplicação entre matrizes. Faz-se necessária uma pesquisa mais profunda sobre esta ferramenta ou suas alternativas.

Do lado da linguagem C, foi testado o framework OpenCL que possibilita o acesso à computação em GPU para várias placas gráficas quais NVidia e ATI. O framework requer alguma configuração inicial para funcionar qual como a escolha da placa gráfica a ser usada e o número de núcleos a ser usados. Estas configurações poderiam ser opcionais, no sentido que uma configuração padrão seria escolhida pelo programa (por exemplo poderia ser escolhida a placa de vídeo atualmente em uso e o número de cores poderia ser predeterminado) e a alteração poderia ser permitida caso o usuário queira. Excluindo essa etapa inicial, a ferramenta foi encapsulada em um executável que, uma vez definidos uma entrada, uma saída e uma função do cálculo, copia os dados em entrada na memória da placa vídeo, execute o cálculo e copia de volta o resultado do cálculo na memória RAM. Uma próxima etapa poderia ser definir três argumentos de entrada para este executável: o percurso ao arquivo contendo os dados em entrada, o percurso ao arquivo contendo a função do cálculo a ser executado na GPU e um percurso de destino aonde será gerado o arquivo com o resultado do cálculo. Dessa forma, o programa pai poderia passar os dados informados pelo usuário a este executável em forma de argumentos ao comando.

Uma alternativa a OpenCL é CUDA, um framework específico para placas de vídeo do fabricante Nvidia. Este framework resulta ter um desempenho um pouco maior se comparado com OpenCL [???]. Em um primeiro teste o framework CUDA apresentou características de sintaxe e funcionamento equivalentes ao OpenCL tais que parece trivial fazer uma camada de abstração genérica que permita de escolher se executar uma ou outra implementação.



4   Notes
=================
[1] http://fifesoft.com/rsyntaxtextarea/
[2] http://asm.ow2.org/
