package dao;

import dao.impl.AddressDaoImplV2;
import model.Address;

public interface AddressDao extends CrudDao<Address> {
    AddressDao INSTANCE = new AddressDaoImplV2();
}
