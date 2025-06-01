package fatec.mkkg.server.facade;

import fatec.mkkg.server.domain.FachadaRequestDTO;
import fatec.mkkg.server.domain.FachadaResponseDTO;

public interface IFachada {

    FachadaResponseDTO salvar(FachadaRequestDTO request);
    FachadaResponseDTO alterar(FachadaRequestDTO request);
    FachadaResponseDTO excluir(FachadaRequestDTO request);
    FachadaResponseDTO consultar(FachadaRequestDTO request);

}
