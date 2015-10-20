
		var app = angular.module('clientRest', [])
		.controller('lista', ['$scope', 'loadLista', '$rootScope', 'loadContato', '$http', 'loadUrl', function($scope, loadLista, $rootScope, loadContato, $http, loadUrl) {
			
			$scope.registros 	= 0;
			$scope.contatos 	= loadLista.getContatos();
			$scope.url 			= loadUrl.get();


			$rootScope.$on('click',function(){
				var contatos 		= loadLista.getContatos();
		        $scope.contatos 	= contatos;
		        $scope.registros 	= contatos.length;
            });
            
            $rootScope.$on('zero',function(){
            	$scope.registros = 0;
            });

			$rootScope.$on('url',function() {
            	$scope.url = loadUrl.get();
            });

            $rootScope.$on('delete',function(){
            	var contato = loadContato.getContato();
            	$scope.acao = 'delete';

				$http({
					url: 'controle.php',
					method: 'POST',
					data: {contato:contato,acao:$scope.acao,url:$scope.url}
				}).success(function (response) {
					loadLista.setContatos(response);
					$('#confirma').modal('toggle');
				}).error(function (response){
					alert('Falha');
				});
            });


            $scope.editar = function(contato) {
            	loadContato.setContato(contato);
            }

            $scope.excluir = function(contato) {
            	loadContato.setContato(contato);
            }

		}])
		.controller('pesquisa', ['$scope', '$http', 'loadLista', 'loadContato', 'loadUrl', '$rootScope', function($scope, $http, loadLista, loadContato, loadUrl, $rootScope) {
			
			$scope.cidade 			= "";
			$scope.contato 			= { "codigo": "", "nome": "", "email": "", "endereco": "", "complemento": "", "cep": "", "cidade": "", "estado": "", "email_alter": "" };  
			$scope.url 				= loadUrl.get();

            $rootScope.$on('url',function(){
            	$scope.url = loadUrl.get();
            });

			$scope.listar = function() {				
				$scope.contato.cidade = $scope.cidade;
				$http({
					url: 'controle.php',
					method: 'POST',
					data: {contato:$scope.contato,acao:'listar',url:$scope.url}
				}).success(function (response) {
				  	loadLista.setContatos(response);
				});
			};
			
			$scope.novo = function() {
				loadContato.novo();
			};

			$scope.urlAtual = function() {
            	loadUrl.setOld($scope.url);
			}

		}])
		.controller('formulario',['$rootScope','$scope','$http', 'loadLista', 'loadContato', 'loadUrl', function($rootScope,$scope, $http, loadLista, loadContato, loadUrl) {

			$scope.contatoForm 		= { "codigo": "", "nome": "", "email": "", "endereco": "", "complemento": "", "cep": "", "cidade": "", "estado": "", "email_alter": "" };   		
			$scope.acao 			= "novo";

			$scope.url = loadUrl.get();

            $rootScope.$on('contato',function(){
            	x = loadContato.getContato();
            	$scope.contatoForm.codigo 		= 	x.codigo;
            	$scope.contatoForm.nome 		= 	x.nome;
            	$scope.contatoForm.email 		= 	x.email;
            	$scope.contatoForm.endereco		= 	x.endereco;
            	$scope.contatoForm.complemento	= 	x.complemento;
            	$scope.contatoForm.cep 			= 	x.cep;
            	$scope.contatoForm.email_alter 	= 	x.email_alter;
            	$scope.contatoForm.cidade		= 	x.cidade;
            	$scope.contatoForm.estado		= 	x.estado;
            	$scope.acao 					= 	'update';
            });

            $rootScope.$on('novo',function(){
				$scope.contatoForm 	= { "codigo": "", "nome": "", "email": "", "endereco": "", "complemento": "", "cep": "", "cidade": "", "estado": "", "email_alter": "" };   		
            	$scope.acao 		= 	'novo';
            });

            $rootScope.$on('url',function(){
            	$scope.url = loadUrl.get();
            });

			$scope.gravar = function() {
				msg = "";
				if ($scope.contatoForm.nome 	== "") { $("#contato_nome").addClass("has-warning"); }
				if ($scope.contatoForm.email 	== "") { $("#contato_email").addClass("has-warning"); }
				if ($scope.contatoForm.cidade 	== "") { $("#contato_cidade").addClass("has-warning"); }
				if ($scope.contatoForm.estado 	== "") { $("#contato_estado").addClass("has-warning"); }
				if ($scope.contatoForm.nome 	== "") { msg += "Informe o nome do contato\n"; }
				if ($scope.contatoForm.email 	== "") { msg += "Informe o email do contato\n"; }
				if ($scope.contatoForm.cidade 	== "") { msg += "Informe a cidade do contato\n"; }
				if ($scope.contatoForm.estado 	== "") { msg += "Informe o estado do contato"; }

				if (msg.length > 1) {
					alert(msg);
					return false;
				}

				$http({
					url: 'controle.php',
					method: 'POST',
					data: {contato:$scope.contatoForm,acao:$scope.acao,url:$scope.url}
				}).success(function (response) {
					//alert(response);
					loadLista.setContatos(response);
					$('#myModal').modal('toggle');
				}).error(function (response){
					alert('Falha');
				});
			};

		}])
		.controller('confirma', ['$scope', '$http', 'loadContato', function($scope, $http, loadContato) {         
			$scope.excluir = function() {
				loadContato.del();
			}

		}])
		.controller('url', ['$rootScope', '$scope', '$http', 'loadUrl', function($rootScope, $scope, $http, loadUrl) {
			$scope.url 		= 	loadUrl.get();

			$scope.gravar 	=	function() {
				loadUrl.set($scope.url);
			}; 

			$rootScope.$on('urlOld',function(){
            	$scope.url = loadUrl.get();
            });

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
		}])
		.service('loadContato',['$rootScope', function($rootScope) {

			var contato = [];

			return {
				getContato: function() {
					return contato;
				},
				setContato: function(c) {
					contato = c;
					$rootScope.$emit('contato');
				},
				novo: function() {
					$rootScope.$emit('novo');
				},
				del: function() {
					$rootScope.$emit('delete');
				}
			};

		}])
		.service('loadUrl',['$rootScope', function($rootScope){

			var url = "http://localhost/wsRest/index.php/contato";

			return {
				setOld: function(urlNova) {
					url = url;
					$rootScope.$emit('urlOld');
				},				
				set: function(urlNova) {
					url = urlNova;
					$rootScope.$emit('url');
					$('#url').modal('toggle');
				},
				get: function() {
					return url;
				}
			}

		}]);