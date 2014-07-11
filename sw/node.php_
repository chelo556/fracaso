
<?php
#Author Marcelo Barrera
#
class nodo{
	public function jsonEmpty(){
	   $arr =
	            array(
	                "fecha" =>" ",
	                "titulo" => ' ',
	                "lenguaje" => ' ',
	                "contenido" => ' ',
	                "imagen" => ' ',
	                "comentarios" =>' '
	          );
	  echo json_encode($arr);
	}

	public function getAll(){

	  $articulos=array();
	  for($i=9; $i>0;$i--){

	    $node = node_load($i);                                 
	    #echo '<br>';
 
	    //fecha
	    $fecha = getdate( $node->created );
	    $fecha = $fecha["mday"]."-".$fecha["mon"]."-".$fecha["year"];
	    #echo '<br>';
   
	    //titulo de la nota, articulo, lo que sea
	    #print($node->title);
	    $titulo = $node->title;
	    #print $comment->comment_body ["und"][0] ["value"];


	    //Nombre de la imagen
	    $body = field_get_items('node',$node, 'field_image');
	    $imagen = ($body[0]['filename']);
	    #echo ($body[0]['filename']);

	    $arr = array('fecha' => $fecha,'titulo'=>$titulo,'idNode'=>$i );
	    array_push($articulos,$arr); 
    
	  }

	  $arr = 
	    array(
	        "articulos" => $articulos    
	  );

	  echo json_encode($arr);  
	}

	public function getNode($nid){
	  #Cargar, obtener el nodo,
	  $node = node_load($nid);                                 
  
	  //fecha
	  $fecha = getdate( $node->created );
	  $fecha = $fecha["mday"]."-".$fecha["mon"]."-".$fecha["year"];
  
	  //titulo de la nota, articulo, lo que sea
	  #print($node->title);
	  $titulo = $node->title;
  
	 //lenguaje
	  #print($node->language);
	  $lenguaje=$node->language;

	  //Contenido del articulo
	  $body = field_get_items('node',$node, 'body');
	  #print (utf8_decode ($body[0]['value']));
	  $contenido = $body[0]['value'];
  
	  //Nombre de la imagen
	  $body = field_get_items('node',$node, 'field_image');
  
	  $imagen = ($body[0]['filename']);
	  #echo ($body[0]['filename']);
	  $rimagen = "/var/www/prototipo/sites/default/files/styles/large/public/field/image/".$imagen;
	  $b64 = base64_encode(file_get_contents($rimagen));
	  $imagen = '<img src="data: '.mime_content_type($rimagen).';base64,'.$b64.'">';
	  $imagen =utf8_encode($imagen);
  

	  //Ruta de la imagen
	  #$body = field_get_items('node',$node, 'field_image');
	  #print $body[0]['uri'];
	  #$imagen = $body[0]['uri'];
  
	  //comentarios
	  $result = db_select('comment') ->fields('comment', array('cid')) ->condition('nid', $node->nid, '=') ->execute();
	  $cids = $result->fetchCol();
	  $comments=comment_load_multiple($cids);
	  $i=1;
	  $comentarios=array();
	  foreach($comments as $comment){
	    #print $comment->comment_body ["und"][0] ["value"];
	    $comentarios[''.$i] =utf8_encode ($comment->comment_body ["und"][0] ["value"]);
	    $i++;
	  }
	  //Información completa del nodo, de manera algo complicada de accesar :S
	  #print_r($node);

	  $arr = 
	    array(
	        "fecha" => $fecha,
	        "titulo" => $titulo,
	        "lenguaje" => $lenguaje,
	        "contenido" => $contenido,
	        "imagen" => $imagen,	
	        "comentarios" => $comentarios    
	  );

	  echo json_encode($arr);
	}
	
	public function addComment($Comentario,$idNode){
	  #$no =array ("<",">");
	  #$si =array ("&lt;","&gt; ");
	  #$ncom=str_replace($no,$si,$Comentario);
	  $ncom =htmlentities($Comentario);

          $comment = (object) array(
            'nid' => $idNode,
            #'cid' => 0,
            #'pid' => 0,
            #'uid' => 1,
            #'mail' => '',
            'is_anonymous' => 1,
            #'homepage' => '',
            'status' => COMMENT_PUBLISHED,
            #'subject' => 'dsk subject',
            'language' => LANGUAGE_NONE,
            'comment_body' => array(
              LANGUAGE_NONE => array(
                0 => array (
                  'value' => $ncom,
                  'format' => 'filtered_html'
                )
              )
            ),
          );
          comment_submit($comment);
          comment_save($comment);
          $arr =
            array(
                "mensaje" => "Comentario agregado");
          echo json_encode($arr);
        }

}
?>
