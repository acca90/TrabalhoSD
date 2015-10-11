<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>ClientREST</title>

        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="js/jquery.js"></script>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="js/angular.js"></script>

    </head>
    <body>
    	


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
        	<!-- Collection of nav links, forms, and other content for toggling -->
        	<div id="navbarCollapse" class="collapse navbar-collapse">
	            <ul class="nav navbar-nav">
	                <li class="active"><a href="#">Listar</a></li>
	                <li><a data-toggle="modal" data-target="#myModal" href="#">Novo</a></li>
	                <li><a href="#">Configurar</a></li>
	            </ul>
        	</div>
      	</nav>
<!--
      	<div class="container">
      	  	<div class="starter-template">
      	    	<table class="table table-striped">
      	      		<thead>
      	        		<tr>
      	          			<th>3311 4799</th>
			      	        <th>Lastname</th>
			      	        <th>Email</th>
	      	        	</tr>
	      	      	</thead>
      	      		<tbody>
		      	        <tr>
		      	          	<td>John</td>
		      	          	<td>Doe</td>
		      	          	<td>john@example.com</td>
		      	        </tr>
		      	        <tr>
		      	          	<td>John</td>
		      	          	<td>Doe</td>
		      	          	<td>john@example.com</td>
		      	        </tr>
		      	        <tr>
		      	          	<td>John</td>
		      	          	<td>Doe</td>
		      	          	<td>john@example.com</td>
		      	        </tr>
      	      		</tbody>
      	    	</table>    
      	   </div>
      	</div>



-->



<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content ">
      	<div class="modal-header">
        	<button type="button" class="close" data-dismiss="modal">&times;</button>
        	<h4 class="modal-title">Novo contato</h4>
      	</div>
      	<div class="modal-body">
			<div class="container">
			    <div class="col-lg-10 col-offset-6 centered">
					<form class="form-horizontal" role="form" id="form-contato">
						<input type='hidden' id='acao' value='novo'>
						<div class="form-group">
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
  			<button type="button" id='contato_submit' class="btn btn-danger">Submit</button>
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
    </body>
</html>