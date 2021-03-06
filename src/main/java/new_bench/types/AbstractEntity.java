/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package new_bench.types;

import java.util.HashMap;

public abstract class AbstractEntity implements Entity 
{
	protected  final long rowNumber;
	public long getRowNumber(){return rowNumber;}
	
	protected EntityInstance entity; 
	
	protected String relationName; 
	protected HashMap<String, Boolean> projMap = new HashMap<String, Boolean>(); // projection map
	
	public String[] headers(){return entity.headers;} 
	public String[] values(){return entity.values;}
	public String[] types(){return entity.types;}
	
	public HashMap<String, Boolean> getProjectionMap(){return entity.projMap;}
	public String getRelationName(){return entity.relationName;}
   
	public void setProjection(String[] projHeaders){entity.setProjection(projHeaders);	}
	public void setPrefix(String prefix){this.entity.setPrefix(prefix); }
	
	public AbstractEntity(long rowNumber){
		this.rowNumber = rowNumber;
		//this.entity = new TpchEntityInstance(headers, types, values); 
	}
	
	public EntityInstance getEntity(){ return entity; }
	
    @Override
    public String toLine() { return entity.toLine();   }
    @Override
    public String toJson(){ return entity.toJson(); }
    @Override
    public String toElasticSearchJson(){
    	String str = "{\"index\": {_index: \"tpch\", _type: \""+ this.relationName + "\", _id: "+ this.rowNumber + "}}";
    	return str + "\n"  + this.toJson();      	
    }
    
    @Override
    public String toXML(){ return entity.toXML(); }
    
    @Override
    public String toCSV(String separator){return entity.toCSV(separator);    }
    
}
