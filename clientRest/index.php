<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>ClientREST</title>

        <!-- Bootstrap -->
        <link href="css/bootstrap.css" rel="stylesheet">

        <!-- Style -->
        <link href="css/style.css" rel="stylesheet">

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="js/jquery.js"></script>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="js/angular.js"></script>

    </head>
    <body ng-app="clientRest">
    	


    	<nav class="navbar navbar-default navbar-static-top">
        <!-- Brand and toggle get grouped for better mobile display -->
        	<div class="navbar-header">
	            <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
	                <span class="sr-only">Toggle navigation</span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	            </button>
            	<a href="#" class="navbar-brand">Brand</a>
        	</div>
        	<!-- Collection of nav links, forms, and other content for toggling  -->
        	<div id="navbarCollapse" class="collapse navbar-collapse" ng-controller="pesquisa">
				<form class="navbar-form navbar-left" role="search">
				  	<div class="form-group" >
				    	<input type="text" ng-model='cidade' ng-value="" class="form-control" placeholder="Cidade">
				  	</div>
				  	<button type="button" ng-click="listar()" class="btn btn-default">Buscar</button>
				</form>
		        <ul class="nav navbar-nav">
	                <li><a data-toggle="modal" data-target="#myModal" href="#">Novo</a></li>
	                <li><a href="#">Configurar</a></li>
	            </ul>
        	</div>
      	</nav>

	<div class="container" ng-controller="lista">
      	<div class="starter-template">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th align='left'>#</th>
						<th align='left'>Nome</th>
						<th align='left'>Endereço</th>
						<th align='left'>Complemento</th>
						<th align='left'>CEP</th>
						<th align='left'>Cidade</th>
						<th align='left'>Estado</th>
						<th align='left'>Email</th>
						<th align='left'>Email Alternativo</th>
					</tr>
				</thead>
				<tbody>
				  	<tr ng-if="registros != 0" ng-repeat="x in contatos">
				    	<td>{{ x.codigo }}</td>
				    	<td>{{ x.nome }}</td>
				    	<td>{{ x.endereco }}</td>
				    	<td>{{ x.complemento }}</td>
				    	<td>{{ x.cep }}</td>
				    	<td>{{ x.cidade }}</td>
				    	<td>{{ x.estado }}</td>
				    	<td>{{ x.email }}</td>
				    	<td>{{ x.email_alter }}</td>
				  	</tr>
				  	<tr>
				  		<td colspan="9" ng-if="registros == 0">Nenhum registro localizado</td>
				  	</tr>
			  	</tbody>
			</table>
		</div>
	</div>


	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
	  <div class="modal-dialog modal-lg">

	    <!-- Modal content-->
	    <div class="modal-content">
	      	<div class="modal-header">
	        	<button type="button" class="close" data-dismiss="modal">&times;</button>
	        	<h4 class="modal-title">Novo contato</h4>
	      	</div>
	      	<div class="modal-body">
				<div class="container">
				    <div class="col-lg-10 col-offset-6 centered">
						<form class="form-horizontal" role="form" id="form-contato">
							<input type='hidden' id='acao' value='novo'>
							<div style="display:none" class="form-group">
						    	<label class="control-label col-md-3" for="contato_codigo">Código:</label>
						    	<div class='col-md-5'>
						    		<input type="text" class="form-control" id='contato_codigo' name="contato_codigo">
						  		</div>
						  	</div>
							<div class="form-group">
						    	<label class="control-label col-md-3" for="contat_nome">Nome:</label>
						    	<div class='col-md-5'>
						    		<input type="text" class="form-control" name="contato_nome">
						  		</div>
						  	</div>
						  	<div class="form-group">
						    	<label class="control-label col-md-3" for="contato_email">Email Principal:</label>
						    	<div class='col-md-5'>
						    		<input type="email" class="form-control" name="contato_email">
						    	</div>
						  	</div>
						  	<div class="form-group">
						    	<label class="control-label col-md-3" for="contato_email_alter">Email Alternativo:</label>
						    	<div class='col-md-5'>
						    		<input type="email" class="form-control" name="contato_email_alter">
						    	</div>
						  	</div>
							<div class="form-group">
						    	<label class="control-label col-md-3" for="contato_endereco">Endereço:</label>
						    	<div class='col-md-5'>
							    	<input type="text" class="form-control" name="contato_endereco">
						    	</div>
						  	</div>
							<div class="form-group">
						    	<label class="control-label col-md-3" for="contato_complemento">Complemento:</label>
						    	<div class='col-md-4'>
							    	<input type="text" class="form-control" name="contato_complemento">
						    	</div>
						  	</div>
							<div class="form-group">
						    	<label class="control-label col-md-3" for="contato_cep">CEP:</label>
						    	<div class='col-md-3'>
							    	<input type="text" class="form-control" name="contato_cep">
						    	</div>
						  	</div>
							<div class="form-group">
						    	<label class="control-label col-md-3" for="contato_cidade">Cidade:</label>
						    	<div class='col-md-5'>
							    	<input type="text" class="form-control" name="contato_cidade">
						    	</div>
						  	</div>
							<div class="form-group">
						    	<label class="control-label col-md-3" for="contato_estado">Estado:</label>
						    	<div class='col-md-3'>
							    	<input type="text" class="form-control" name="contato_estado">
						    	</div>
						  	</div>
						</form>
				    </div>
				</div>
	      	</div>
	      	<div class="modal-footer">
	  			<button type="button" id='contato_submit' class="btn btn-default">Confirmar</button>
	  			<button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
	      	</div>
	   	</div>

	  </div>
	</div>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script>

    	$("#contato_submit").click(function() {

    		var form = $("#form-contato").serializeArray();
    		var acao = $("#acao").val();

    		$.ajax({
    			url: "controle.php",
    			data: {acao:acao,contato:form},
    			success: function(retorno) {
    				alert(retorno);
    			}
    		});

    	});


    </script>

	<script>

		var app = angular.module('clientRest', [])
		.controller('lista', ['$scope', 'loadLista', '$rootScope', function($scope, loadLista, $rootScope) {
			
			$scope.registros 	= 0;
			$scope.contatos 	= loadLista.getContatos();
			$rootScope.$on('click',function(){
				var contatos 	= loadLista.getContatos();
		        $scope.contatos = contatos;
		        $scope.registros = contatos.length;
            });
            $rootScope.$on('zero',function(){
            	$scope.registros = 0;
            });
		}])
		.controller('pesquisa', ['$scope', '$http', 'loadLista', function($scope, $http, loadLista) {
			$scope.cidade = "";
			$scope.listar = function() {

				url 	= 	"http://localhost/wsRest/index.php/contato";
				cidade 	= 	$scope.cidade;
				if (cidade.length > 0) url = url + '/' + cidade;
				
				$http.get(url
				).success(function (response) {
				  	loadLista.setContatos(response);
				});

			};
		}])
		.service('loadLista', ['$rootScope', function($rootScope) {
			var contatos 	= 	[];
			var cidade 		=	"";
			return {
				getCidade: function() {
					return cidade;
				},
				setCidade: function(c) {
					cidade = c;
				},
            	getContatos: function () {
                	return contatos;
            	},
	            setContatos: function(c) {
	            	if (c.codigo == 0) {
						$rootScope.$emit('zero');
	            	} else {
	            		contatos = c;
	                	$rootScope.$emit('click');
	            	}
	        	}
        	};
		}]); 

	</script>

    </body>
</html>