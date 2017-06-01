package com.xw.mvPlugin;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * @goal mv
 * 
 * @phase process-sources 
 */
public class MvMojo  extends AbstractMojo
{
    
	/** 
     * @parameter expression="${project.basedir}" 
     * @required 
     * @readonly 
     */  
    private File basedir;  
	
	/**
	 *  @parameter 
	 */
    private String sourceFile;//源文件夹
    /**
     *  @parameter 
     */
    private String targetFile;//目标文件夹
    /**
     *  @parameter 
     */
    private int deleteFlag;//是否删除源文件夹 0:不删;1:删除

    public void execute() throws MojoExecutionException {
    	if("".equals(sourceFile)||null == sourceFile)
    		throw  new MojoExecutionException("sourceFile 不能为空");
    	
    	if("".equals(targetFile)||null == targetFile)
    		throw  new MojoExecutionException("targetFile 不能为空");
    	
    	String base = basedir.getAbsolutePath().substring(0,basedir.getAbsolutePath().lastIndexOf(File.separator));
        File targetF = new File(base+targetFile);
        if ( !targetF.exists()) {
        	targetF.mkdirs();
        }

        File sourceF = new File(sourceFile);
        if(sourceF.isDirectory()){
        	File[] files = sourceF.listFiles();
        	for(File file:files){
        		file.renameTo(new File(targetF+"\\"+file.getName()));
        		file.delete();
        	}
        }
        if(deleteFlag == 1){
        	sourceF.delete();
        }
    }

	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}

	public void setTargetFile(String targetFile) {
		this.targetFile = targetFile;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
    
}
