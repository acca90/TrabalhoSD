<?php 
	
	class contato {

		private $contato 	=	array();
		private $url;
		private $delete;

		public function __construct($contato,$url) {
			$this->contato 	= json_encode($contato);
			$this->url 		= $url;
			$this->delete 	= false;
		}

		/* CONEXÃO COM WEBSERVICE */
		public function curl_contato(
			$OPT 	= 	"",
			$TIPO	=	"",
			$GET 	= 	false) 
		{

			$urlSperador = "";
			$cidade 	 = "";
			$id 		 = "";

			if ($GET) {
				$this->contato = json_decode($this->contato);
				if (strlen($this->contato->cidade) > 0) 
					$urlSperador='/';
				$cidade 	 = $this->contato->cidade;
			}

			if ($this->delete) {
				$contato 		= 	json_decode($this->contato);
				$id 			=	$contato->codigo;
				$urlSperador 	=	'/';
			}


	   		$curl = curl_init($this->url.$urlSperador.$cidade.$id);

		   	curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
		   	/* PARA METODOS POST, PUT e DELETE */
		   	if (!$GET) curl_setopt($curl, $OPT, $TIPO);
		   	if (!$GET) curl_setopt($curl, CURLOPT_POSTFIELDS, $this->contato);

			curl_setopt($curl, CURLOPT_HTTPHEADER, array('Accept: application/json', 'Content-Type: application/json'));

		   	$curl_response = curl_exec($curl);
		   	curl_close($curl);
		   	return $curl_response;
		}

		/* INSERE CONTATO */
		public function novo() {
			return $this->curl_contato(CURLOPT_POST,true); 
		}

		/* ALTERA CONTATO */
		public function update() {
			return $this->curl_contato(CURLOPT_CUSTOMREQUEST,"PUT"); 
		}

		/* DELETA CONTATO */
		public function delete() {
			$this->delete = true;
			return $this->curl_contato(CURLOPT_CUSTOMREQUEST,"DELETE"); 
		}

		/* LISTA CONTATO */
		public function listar() {
			return $this->curl_contato("","",true);
		}




	}


?>