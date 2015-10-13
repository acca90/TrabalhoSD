
		var app = angular.module('clientRest', [])
		.controller('lista', ['$scope', 'loadLista', '$rootScope', 'loadContato', function($scope, loadLista, $rootScope, loadContato) {
			
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

            $scope.editar = function(contato) {
            	loadContato.setContato(contato);
            }


		}])
		.controller('pesquisa', ['$scope', '$http', 'loadLista', 'loadContato', function($scope, $http, loadLista, loadContato) {
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
			
			$scope.novo = function() {
				loadContato.novo();
			};

		}])
		.controller('formulario',['$rootScope','$scope','$http', 'loadLista', 'loadContato', function($rootScope,$scope, $http, loadLista, loadContato) {

			$scope.contatoForm 		= { "codigo": "", "nome": "", "email": "", "endereco": "", "complemento": "", "cep": "", "cidade": "", "estado": "", "email_alter": "" };   		
			$scope.acao 			= "novo";

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
					data: {contato:$scope.contatoForm,acao:$scope.acao}
				}).success(function (response) {
					//alert(response);
					loadLista.setContatos(response);
					$('#myModal').modal('toggle');
				}).error(function (response){
					alert('Falha');
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
				}
			};

		}]); 