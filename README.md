# Tenebris: Sistema Híbrido de recomendações para obras acadêmicas

Um sistema de recomendação híbrido de trabalhos acadêmicos para apoio a pesquisa científica. O sistema de Recomendação é baseado em componentes de filtragem de informação que implementam as técnicas de Filtragem Colaborativa e Baseada em Conteúdo. Desenvolvido para Web, utilizando frameworks, tais como, Lucene, Mahout e Angular JS, o projeto encontra-se em fase de desenvolvimento dentro da Escola Superior de Tecnologia - EST, desde 2016.

1. [Instalação](https://github.com/thiagomarques2015/tenebris/wiki/Instala%C3%A7%C3%A3o)
1. [Funcionalidades](https://github.com/thiagomarques2015/tenebris/wiki/Funcionalidades)
1. Equipe

# Requisitos

O projeto necessita de:

1. [Tomcat >= 7.0](https://tomcat.apache.org/download-70.cgi)
2. [Mysql](https://www.mysql.com/) ou [MariaDB](https://mariadb.org/)

# Instalação

Clonando o projeto

`$ git clone https://github.com/thiagomarquesrocha/tenebris.git /tenebris`

Depois de clonar o projeto, certifique-se de que você está visualizando o branch __master__

```git
$ git branch
  front
  test
* master
```

__Instalando o banco de dados__


Importe o banco da pasta **database** para seu gerenciador de banco de dados, utilizando um gerenciador com suporte a Mysql/MariaDB.

```cmd
bin
=> database <=
src
```

O arquivo dentro desta pasta é :

```
import.sql
```

Importe para seu SGBD e sua base de dados estará pronta para uso.

__Importando para o Eclipse__

Agora o que resta é importar o projeto para seu Eclipse e executar. 

Para fazer isso você precisa ir em:

```
1. File
1. Import projects from Folder
```

Procure pela pasta no seu computador onde o projeto foi salvo.

Pronto! Agora o projeto esta pronto para uso.

# Executar

Execute pelo Eclipse e abra no seu navegador o endereço:

```
localhost:8080/tenebris2016
```


_Escola Superior de Tecnologia - EST, desde 2016_
