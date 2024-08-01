package com.example.LaptopShopClone.ServiceInterface;

import com.example.LaptopShopClone.Entity.VaiTro;

public interface VaiTroService {
	VaiTro getVaiTroByName(String name);
	void addVaiTro(VaiTro vaiTro);
}
