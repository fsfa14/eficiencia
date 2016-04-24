package control;

public class Nom_e_ID {
	
	
	private int[] IDs;
	private String[] nombres;
	
	public Nom_e_ID(int[] iDs, String[] nombres) {
		this.setIDs(iDs);
		this.setNombres(nombres);
	}

	public int getIdPosicion(int seleccion){
		
		int id;
		id=IDs[seleccion];			
		return id;
	}
	
	public String getNombrePosicion(int seleccion){
		
		String retorno;
		retorno = nombres[seleccion];
		return retorno;
	}
	
	public Nom_e_ID sinRepetir(){
		
		for(int i=0;i<IDs.length;i++){
			
			for(int j=i;j<IDs.length;j++){
			
				if(i!=j){
					
					if(IDs[i] == IDs[j]){
						
						nombres[j]="";
					}
				}
			}
		}
		
		int n=1;
		for(int k=0;k<IDs.length;k++){
			
			if(!(nombres[k].equals(""))){
				n++;
			}
		}
		
		String[] nombres_u = new String[n];
		int[] IDs_u = new int[n];
		n=0;
		for(int i=0;i<IDs.length;i++){
			
			if(!(nombres[i].equals(""))){
				nombres_u[n] = nombres[i];
				IDs_u[n] = IDs[i];
				n++;
			}
		}
		
		for(int i=0;i<IDs_u.length-1;i++){
			for(int j=0;j<IDs_u.length-i-1;j++){
				if(IDs_u[j]<IDs_u[j+1]){
					int aux=IDs_u[j];
					String aux2=nombres_u[j];
					IDs_u[j]=IDs_u[j+1];
					nombres_u[j]=nombres_u[j+1];
					IDs_u[j+1]=aux;
					nombres_u[j+1]=aux2;
					
				}
			}
		}
		Nom_e_ID datos = new Nom_e_ID (IDs_u, nombres_u);
				
		return datos;
	}
	

	public String[] getNombres() {
		return nombres;
	}

	public void setNombres(String[] nombres) {
		this.nombres = nombres;
	}

	public int[] getIDs() {
		return IDs;
	}

	public void setIDs(int[] iDs) {
		IDs = iDs;
	}
	
	
	
	

}
