package com.mta.chen;

public class WeddingDress {//class-����� �������
private float Size;//variables-���� ����
private String designerName;
public String getDesignerName() {
	return designerName;
	
//WeddingDress.setdes//("alon livne");
//WeddingDress.setcreatcr//("alon livne");
}
public float getSize() {
	return Size;
}
public void setSize(float size) {
	Size = size;
}
public void setDesignerName(String designerName) {
	this.designerName = designerName;
}

public String stringHtml()
{
	String ret = "<br> des name: " + designerName + "<br> size:" + Size;
	return ret;
	}
}
//�����- ���� ��

