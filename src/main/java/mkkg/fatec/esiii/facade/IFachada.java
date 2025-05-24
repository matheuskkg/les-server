package mkkg.fatec.esiii.facade;

import mkkg.fatec.esiii.domain.FachadaRequestDTO;
import mkkg.fatec.esiii.domain.FachadaResponseDTO;

public interface IFachada {

    FachadaResponseDTO salvar(FachadaRequestDTO request);
    FachadaResponseDTO alterar(FachadaRequestDTO request);
    FachadaResponseDTO excluir(FachadaRequestDTO request);
    FachadaResponseDTO consultar(FachadaRequestDTO request);

}
