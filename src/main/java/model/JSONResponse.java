package model;

import java.util.List;

public interface JSONResponse<T> {
	List<T> getList();
	boolean isRequest();
}
