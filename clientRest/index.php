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
	                <li><a ng-click="novo()" data-toggle="modal" data-target="#myModal" href="#">Novo</a></li>
	                <li><a ng-click="urlAtual()"data-toggle="modal" data-target="#url" href="#" href="#">Configurar</a></li>
	            </ul>
        	</div>
      	</nav>




	<div class="container" ng-controller="lista">

		<div class="panel panel-default">
	  		<!-- Default panel contents -->
	  		<div class="panel-heading">Contatos </div>
			
			 <table class="table table-striped table-hover">
					<thead>
						<tr>
							<th align='left'>#</th>
							<th align='left'>Nome</th>
							<th align='left'>Endereço</th>
							<th align='left'>Complemento</th>
							<th align='left'>Cidade</th>
							<th align='left'>Email</th>
							<th align='left'>Email Alternativo</th>
							<th style="width: 70px;"></th>
						</tr>
					</thead>
					<tbody>
					  	<tr ng-if="registros != 0" ng-repeat="x in contatos">
					    	<td>{{ x.codigo }}</td>
					    	<td>{{ x.nome }}</td>
					    	<td>{{ x.endereco }}</td>
					    	<td>{{ x.complemento }}</td>
					    	<td>{{ x.cidade }}</td>
					    	<td>{{ x.email }}</td>
					    	<td>{{ x.email_alter }}</td>
					    	<td>
					    		<button ng-click="editar(x)" data-toggle="modal" data-target="#myModal" type="button" class="btn btn-danger btn-xs">
					    			<i class='glyphicon glyphicon-pencil'></i>
					    		</button>
					    		<button ng-click="excluir(x)" data-toggle="modal" data-target="#confirma" type="button" class="btn btn-danger btn-xs">
					    			<i class='glyphicon glyphicon-trash'></i>
					    		</button>
							</td>
					  	</tr>
					  	<tr>
					  		<td colspan="9" ng-if="registros == 0">Nenhum registro localizado</td>
					  	</tr>
				  	</tbody>
				</table> 


			</div>
		</div>

    <div ng-controller="url"  class="modal fade" id="url" tabindex="-1" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">Insira a URL do WebService</h4>
                </div>
                <div class="modal-body">
				<div class="container">
				    <div class="col-md-6 ">
							<div class='col-md-12'>
								<input ng-model="url" ng-value="url" id='url' type="text" class="form-control">
							</div>
				    </div>
				</div>					

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                    <button ng-click="gravar()" class="btn btn-danger btn-ok">Gravar</button>
                </div>
            </div>
        </div>
    </div>

    <div ng-controller="confirma"  class="modal fade" id="confirma" tabindex="-1" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">Confirme</h4>
                </div>
                <div class="modal-body">
                	Tem certeza que deseja excluir este contato?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                    <button ng-click="excluir()" class="btn btn-danger btn-ok">Excluir</button>
                </div>
            </div>
        </div>
    </div>

	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog" ng-controller='formulario'>
	  <div class="modal-dialog modal-lg">
	    <!-- Modal content-->
	    <div class="modal-content" >
	      	<div class="modal-header">
	        	<button type="button" class="close" data-dismiss="modal">&times;</button>
	        	<h4 class="modal-title">Novo contato</h4>
	      	</div>
	      	<div class="modal-body">
				<div class="container">
				    <div class="col-lg-10 col-offset-6 centered">
						<form class="form-horizontal" role="form" id="form-contato">
							
							<input ng-model="acao" ng-value="acao" value="" type='hidden'>

							<div style="display:none" class="form-group">
						    	<label class="control-label col-md-3" for="contato_codigo">Código</label>
						    	<div class='col-md-5'>
						    		<input ng-model="contatoForm.codigo" ng-value="contatoForm.codigo" type="text" class="form-control">
						  		</div>
						  	</div>
							<div class="form-group" id="contato_nome">
						    	<label class="control-label vermelho col-md-3" for="contato_nome">Nome</label>
						    	<div class='col-md-5'>
						    		<input ng-model="contatoForm.nome" ng-value="contatoForm.nome"  type="text" class="form-control">
						  		</div>
						  	</div>
						  	<div class="form-group" id="contato_email">
						    	<label class="control-label vermelho col-md-3" for="contato_email">Email Principal</label>
						    	<div class='col-md-5'>
						    		<input ng-model="contatoForm.email" ng-value="contatoForm.email"  type="text" class="form-control">
						    	</div>
						  	</div>
						  	<div class="form-group">
						    	<label class="control-label col-md-3" for="contato_email_alter">Email Alternativo</label>
						    	<div class='col-md-5'>
						    		<input ng-model="contatoForm.email_alter" ng-value="contatoForm.email_alter" type="text" class="form-control">
						    	</div>
						  	</div>
							<div class="form-group">
						    	<label class="control-label col-md-3" for="contato_endereco">Endereço</label>
						    	<div class='col-md-5'>
							    	<input ng-model="contatoForm.endereco" ng-value="contatoForm.endereco" type="text" class="form-control">
						    	</div>
						  	</div>
							<div class="form-group">
						    	<label class="control-label col-md-3" for="contato.complemento">Complemento</label>
						    	<div class='col-md-4'>
							    	<input ng-model="contatoForm.complemento" ng-value="contatoForm.complemento" type="text" class="form-control">
						    	</div>
						  	</div>
							<div class="form-group">
						    	<label class="control-label col-md-3" for="contato_cep">CEP</label>
						    	<div class='col-md-3'>
							    	<input ng-model="contatoForm.cep" ng-value="contatoForm.cep" type="text" class="form-control">
						    	</div>
						  	</div>
							<div class="form-group" id="contato_cidade">
						    	<label class="control-label vermelho col-md-3" for="contato.cidade">Cidade</label>
						    	<div class='col-md-5'>
							    	<input ng-model="contatoForm.cidade" ng-value="contatoForm.cidade" type="text" class="form-control">
						    	</div>
						  	</div>
							<div class="form-group" id="contato_estado"> 
						    	<label class="control-label vermelho col-md-3" for="contato_estado">Estado</label>
						    	<div class='col-md-3'>
							    	<input ng-model="contatoForm.estado" ng-value="contatoForm.estado" type="text" class="form-control">
						    	</div>
						  	</div>
						</form>
				    </div>
				</div>
	      	</div>
	      	<div class="modal-footer">
	  			<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
	  			<button type="button" ng-click='gravar()'class="btn btn-danger">Confirmar</button>
	      	</div>
	   	</div>

	  </div>
	</div>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/base.js"></script>
    </body>
</html>