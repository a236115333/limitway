package com.limitway.current;

import java.util.List;

/**
 * 树形结构
 * @Description:
 * @author hyj
 * @date 2020/06/19
 * @copyright:
 */
public class ConfigTreeNode {
	

	private List<ConfigTreeNode> children;
	
	/**
	 * 主键id
	 */
	private String id;

	@Override
	public String toString() {
		return "ConfigTreeNode{" +
				"children=" + children +
				", id='" + id + '\'' +
				", parent='" + parent + '\'' +
				", text='" + text + '\'' +
				", type='" + type + '\'' +
				", code='" + code + '\'' +
				", state=" + state +
				", codeType='" + codeType + '\'' +
				", paramName1='" + paramName1 + '\'' +
				", paramName2='" + paramName2 + '\'' +
				", paramName3='" + paramName3 + '\'' +
				", paramName4='" + paramName4 + '\'' +
				'}';
	}

	/**
	 * 父类id
	 */
	private String parent;
	/**
	 * 显示名称
	 */
	private String text;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * code
	 */
	private String code;
	/**
	 * 
	 */
	private State state;
	
	private String codeType;
	
	/**
	 * 参数1
	 */
	private String paramName1;
	/**
	 * 参数2
	 */
	private String paramName2;
	/**
	 * 参数3
	 */
	private String paramName3;
	/**
	 * 参数4
	 */
	private String paramName4;
	
	
	

	public List<ConfigTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<ConfigTreeNode> children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}



	public String getParamName1() {
		return paramName1;
	}

	public void setParamName1(String paramName1) {
		this.paramName1 = paramName1;
	}

	public String getParamName2() {
		return paramName2;
	}

	public void setParamName2(String paramName2) {
		this.paramName2 = paramName2;
	}

	public String getParamName3() {
		return paramName3;
	}

	public void setParamName3(String paramName3) {
		this.paramName3 = paramName3;
	}

	public String getParamName4() {
		return paramName4;
	}

	public void setParamName4(String paramName4) {
		this.paramName4 = paramName4;
	}



	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}



	class State{
		
		private boolean opened;
		
		private boolean checked;

		public boolean isOpened() {
			return opened;
		}

		public void setOpened(boolean opened) {
			this.opened = opened;
		}

		public boolean isChecked() {
			return checked;
		}

		public void setChecked(boolean checked) {
			this.checked = checked;
		}
		
	}
	
	
}
